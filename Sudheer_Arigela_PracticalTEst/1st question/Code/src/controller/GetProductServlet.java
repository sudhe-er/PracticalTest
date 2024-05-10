package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.Dataaccess;

@WebServlet("/GetProductServlet")

public class GetProductServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		Dataaccess dal = new Dataaccess();
		// List<Product> parr = ;
		req.setAttribute("productList", dal.getProductList());
		req.setAttribute("len", dal.getProductList().size());
		try {
			req.getRequestDispatcher("onlinestore.jsp").forward(req, res);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

	}
}