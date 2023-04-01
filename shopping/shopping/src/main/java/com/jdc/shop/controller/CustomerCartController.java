package com.jdc.shop.controller;

import java.io.IOException;

import com.jdc.shop.model.service.ProductService;
import com.jdc.shop.utilities.Integers;
import com.jdc.shop.utilities.ShoppingCart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {
				"/cart/add",
				"/cart/show"
		} , 
		loadOnStartup = 1)
public class CustomerCartController extends AbstractController{

	private static final long serialVersionUID = 1L;
	private static final String MY_CART = "cart";
	
	private ProductService productService;
	
	@Override
	public void init() throws ServletException {
		productService = new ProductService(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case "/cart/add": {
			addToCart(req, resp);
			break;
		}
		case "/cart/show": {
			forward(req, resp, "public/cart/contents");
			break;
		}
		default:
		}
	}

	private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		var productId = Integers.parse(req.getParameter("id"));
		
		var session = req.getSession(true);
		ShoppingCart cart = (ShoppingCart) session.getAttribute(MY_CART);
		
		if(null == cart) {
			cart = new ShoppingCart();
			session.setAttribute(MY_CART, cart);
		}
		
		
		if(!cart.add(productId)) {
			var product = productService.findById(productId);
			cart.addNewProduct(product);
		}
		
		resp.sendRedirect(getServletContext().getContextPath().concat("/products?product-id=%d".formatted(productId)));
	}




}