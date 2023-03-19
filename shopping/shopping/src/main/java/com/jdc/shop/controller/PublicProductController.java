package com.jdc.shop.controller;

import java.io.IOException;

import com.jdc.shop.utilities.Integers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/products"},
		loadOnStartup = 1
)
public class PublicProductController extends AbstractController{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var id = Integers.parse(req.getParameter("product-id"));
		req.setAttribute("main", "master");
		req.setAttribute("sub", "product");
		
		if(id == 0) {
			search(req, resp);
		} else {
			showDetails(req, resp, id);
		}
	}

	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get Search Params
		
		// Search with service
		
		// set result to request scope
		
		// forward to home.jsp
		forward(req, resp, "home");
	}

	private void showDetails(HttpServletRequest req, HttpServletResponse resp, int productId) throws ServletException, IOException {
		// Find Product Details info by product id
		
		// set result to request scope

		// forward to /product/details.jsp
		forward(req, resp, "product/details");
	}

}