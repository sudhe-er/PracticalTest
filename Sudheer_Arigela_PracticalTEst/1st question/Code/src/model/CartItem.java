package model;

public class CartItem {
	private int id;
	private String name;
	private double price;
	private String image;
	private int quantity; // Quantity of this item in the cart

	// Constructor
	public CartItem(int id, String name, double price, String image) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.quantity = 1; // Initialize quantity to 1 when adding a new item to cart
	}

	// Getter and setter methods for quantity
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// Other getter methods for item properties
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getImage() {
		return image;
	}

	public double getShippingCharges() {
		// TODO Auto-generated method stub
		return 0;
	}
}