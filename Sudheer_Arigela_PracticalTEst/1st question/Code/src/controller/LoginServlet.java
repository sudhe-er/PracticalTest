package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SearchCustomer;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -173319121081412032L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("username");
		String password = request.getParameter("password");
		SearchCustomer sc = new SearchCustomer();
		if (sc.searchCustomer(email, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("cname", email);
			response.setContentType("plain/text");
			PrintWriter out = response.getWriter();
			out.print("Success");
		} else {
			response.setContentType("plain/text");
			PrintWriter out = response.getWriter();
			out.print("Error");
		}
	}

}
