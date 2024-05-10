package controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class GetPrice
 */
@WebServlet("/GetPrice")
public class GetPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetPrice() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Read the JSON data from the request
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String json = sb.toString();

			// Parse JSON string to retrieve items
			JSONArray itemsArray = new JSONArray(json);

			// Process items (calculate total cost, update database, etc.)
			double totalCost = 0.0;
			for (int i = 0; i < itemsArray.length(); i++) {
				JSONObject item = itemsArray.getJSONObject(i);
				int quantity = item.getInt("quantity");
				double price = item.getDouble("price");

				// Calculate subtotal for the item
				double subtotal = quantity * price;

				// Accumulate subtotal to calculate total cost
				totalCost += subtotal;

				// You can perform other operations here, such as updating database with quantity changes
			}

			response.setContentType("text/plain");
			response.getWriter().write(String.valueOf(totalCost));
		} catch (JSONException e) {
			// Handle JSON parsing errors
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("Invalid JSON data: " + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
}