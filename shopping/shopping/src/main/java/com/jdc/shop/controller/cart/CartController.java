package com.jdc.shop.controller.cart;

import java.io.IOException;

import com.jdc.shop.controller.AbstractController;
import com.jdc.shop.model.service.AddressService;
import com.jdc.shop.model.service.CheckOutService;
import com.jdc.shop.model.service.ProductService;
import com.jdc.shop.utilities.Integers;
import com.jdc.shop.utilities.LoginUser;
import com.jdc.shop.utilities.ShoppingCart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {
				"/cart/add",
				"/cart/remove",
				"/cart/checkout",
		} , 
		loadOnStartup = 1)
public class CartController extends AbstractController{

	private static final long serialVersionUID = 1L;
	private static final String MY_CART = "cart";
	
	private ProductService service;
	private CheckOutService checkOutService;
	private AddressService addressService;
	
	@Override
	public void init() throws ServletException {
		service = new ProductService(dataSource);
		checkOutService = new CheckOutService(dataSource);
		addressService = new AddressService(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("main", "cart");
		if("/cart/add".equals(req.getServletPath())) {
			addToCart(req, resp);
		} else if("/cart/remove".equals(req.getServletPath())) {
			removeFromCart(req, resp);
		} else {
			LoginUser login = (LoginUser) req.getSession().getAttribute("login");
			if(null != login) {
				req.setAttribute("addresses", addressService.findAddressForCustomer(login.getId()));
			}
			checkOut(req, resp);
		}
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var cart = (ShoppingCart)req.getSession().getAttribute("cart");
		var login = (LoginUser)req.getSession(true).getAttribute("login");
		
		var id = checkOutService.purchase(login.getId(), cart);
		
		redirect(resp, "/members/order?id=%d".formatted(id));
	}
	
	private void checkOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var login = req.getSession(true).getAttribute("login");
		if(null == login) {
			forward(req, resp, "cart/view");
		} else {
			redirect(resp, "/customer/cart/payment");
		}
	}

	private void removeFromCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		var productId = Integers.parse(req.getParameter("id"));
		
		var session = req.getSession(true);
		ShoppingCart cart = (ShoppingCart) session.getAttribute(MY_CART);
		
		if(null == cart) {
			cart = new ShoppingCart();
			session.setAttribute(MY_CART, cart);
		}
		
		
		cart.removeItem(productId);
		
		if(cart.getItems().isEmpty()) {
			redirect(resp, "/");
			return;
		}
		
		redirect(resp, "/cart/checkout");
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
		
		if(null != req.getParameter("cart")) {
			redirect(resp, "/cart/checkout");
			return;
		}
		
		redirect(resp, "/products?product-id=%d".formatted(productId));
	}

}