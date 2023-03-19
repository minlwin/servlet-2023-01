package com.jdc.shop.controller;

import java.io.IOException;

import com.jdc.shop.model.service.ProductService;
import com.jdc.shop.utilities.Integers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/sale/product", "/sale/product/edit", "/sale/product/details", "/sale/product/photo",

}, loadOnStartup = 1)
public class SaleProductController extends AbstractController {

	private static final long serialVersionUID = 1L;

	private ProductService service;
	
	@Override
	public void init() throws ServletException {
		service = new ProductService(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("main", "master");
		req.setAttribute("sub", "product");
		
		var id = Integers.parse(req.getParameter("id"));

		if (id > 0) {
			req.setAttribute("dto", service.findById(id));
		}

		var page = switch (req.getServletPath()) {
		case "/sale/product/edit" -> "edit";
		case "/sale/product/details" -> "details";
		default -> {
			var category = req.getParameter("category");
			var keyword = req.getParameter("keyword");
			req.setAttribute("list", service.search(category, keyword));
			yield "list";
		}
		};

		forward(req, resp, "/product/".concat(page));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		switch (req.getServletPath()) {
		
		case "/sale/product/photo" -> {
			// Photo Upload
		}

		default -> {
			// Save Product
		}
		
		}

	}

}