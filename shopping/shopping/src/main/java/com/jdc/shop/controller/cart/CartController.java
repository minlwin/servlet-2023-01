package com.jdc.shop.controller.cart;

import java.io.IOException;

import com.jdc.shop.controller.AbstractController;
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
				"/cart/checkout",
		} , 
		loadOnStartup = 1)
public class CartController extends AbstractController{

	private static final long serialVersionUID = 1L;
	private static final String MY_CART = "cart";
	
	private ProductService service;
	
	@Override
	public void init() throws ServletException {
		service = new ProductService(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if("/cart/add".equals(req.getServletPath())) {
			addToCart(req, resp);
		} else {
			checkOut(req, resp);
		}
	}
	
	private void checkOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var login = req.getSession(true).getAttribute("login");
		if(null == login) {
			forward(req, resp, "public/cart/contents");
		} else {
			resp.sendRedirect(getServletContext().getContextPath().concat("/customer/cart/payment"));
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
			var product = service.findById(productId);
			cart.addNewProduct(product);
		}
		
		resp.sendRedirect(getServletContext().getContextPath().concat("/products?product-id=%d".formatted(productId)));
	}




}