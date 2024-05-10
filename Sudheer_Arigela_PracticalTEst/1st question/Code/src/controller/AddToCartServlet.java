package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dataaccess;
import model.Cart;
import model.CartItem;
import model.ServiceableRegion;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7354596430124874293L;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			doPost(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get product details from request parameters
		int productId = Integer.parseInt(request.getParameter("id"));
		String productName = request.getParameter("name");
		double productPrice = Double.parseDouble(request.getParameter("price"));
		String productImage = request.getParameter("image");

		// Get PIN code entered by the user
		String enteredPin = request.getParameter("pinCode");
		System.out.println("entered pin is: " + enteredPin);

		// Validate the PIN code against serviceable regions
		boolean isAvailableInRegion = checkAvailability(enteredPin);

		if (!isAvailableInRegion) {
			// If the product is not available in the region, show an error message
			response.setContentType("text/html");
			response.getWriter().write("This product is not deliverable to your region.......");
			return;
		}
		// Get or create session
		HttpSession session = request.getSession();

		// Get the cart from session, or create a new one if it doesn't exist
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		// Check if the product already exists in the cart
		List<CartItem> items = cart.getItems();
		boolean productFound = false;

		for (CartItem item : items) {
			if (item.getId() == productId) {
				// Product already exists in the cart, update the quantity
				item.setQuantity(item.getQuantity() + 1); // Increment quantity
				System.out.println("q set");
				System.out.println(item.getQuantity());
				productFound = true;
				break;
			}
		}

		if (!productFound) {
			// Product does not exist in the cart, add a new item
			CartItem newItem = new CartItem(productId, productName, productPrice, productImage);
			cart.addItem(newItem);
		}
		// System.out.println("this is cart");
		// System.out.println();
		// Send success response

		// Calculate shipping charges based on total order value
		double totalOrderValue = calculateTotalOrderValue(cart);
		double shippingCharges = calculateShippingCharges(totalOrderValue);
		double shippingChargesWithGST = applyGST(shippingCharges);

		response.setContentType("text/html");
		response.getWriter().write("Product added to cart successfully.....!");

	}

	// Method to check availability based on the PIN code
	private boolean checkAvailability(String enteredPin) {
		// Retrieve serviceable regions from the database
		System.out.println("Checking regionss....");
		Dataaccess dl = new Dataaccess();
		List<ServiceableRegion> serviceableRegions = null;
		try {
			serviceableRegions = dl.getServiceableRegions();
		} catch (Exception e) {
			e.printStackTrace(); // Handle the exception appropriately
			return false;
		}

		// Check if the entered PIN code falls within any serviceable region
		// int pinCode = Integer.parseInt(enteredPin);

		// Check if enteredPin is null or empty
		if (enteredPin == null || enteredPin.isEmpty()) {
			return false; // If pin is not provided, consider it unavailable
		}

		// Parse enteredPin to integer
		int pinCode;
		try {
			pinCode = Integer.parseInt(enteredPin);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false; // If pin is not a valid integer, consider it unavailable
		}

		for (ServiceableRegion region : serviceableRegions) {
			System.out.println("Pincode is: " + pinCode);
			if (pinCode >= region.getSrrgPinFrom() && pinCode <= region.getSrrgPinTo() && region.getloc() == 1) {
				return true; // Product is available in the region
			}
		}

		return false; // Product is not available in the region
	}

	private double calculateTotalOrderValue(Cart cart) {
		List<CartItem> items = cart.getItems();
		double total = 0;
		for (CartItem item : items) {
			total += item.getPrice() * item.getQuantity();
		}
		return total;
	}

	private double calculateShippingCharges(double totalOrderValue) {
		// Retrieve shipping charges based on the total order value from the database
		// You can modify this method to fetch the appropriate shipping charges from the database
		// For simplicity, let's assume a flat shipping charge of $10 for orders below $1000 and free shipping above
		// $1000
		if (totalOrderValue < 1000) {
			return 10;
		} else {
			return 0;
		}
	}

	private double applyGST(double shippingCharges) {
		// GST rate
		double gstRate = 0.18; // 18%

		// Calculate GST on shipping charges
		double gstAmount = shippingCharges * gstRate;

		// Add GST to the shipping charges
		double totalShippingCharges = shippingCharges + gstAmount;

		return totalShippingCharges;
	}
}
