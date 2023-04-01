package com.jdc.shop.controller;

import java.io.IOException;

import com.jdc.shop.model.dto.form.PurchaseAddressForm;
import com.jdc.shop.model.service.AccountService;
import com.jdc.shop.model.service.PaidInfoService;
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
				"/cart/show",
				"/customer/cart/checkout",
				"/customer/cart/shipping",
				"/customer/cart/payment",
				"/customer/cart/confirm"
		} , 
		loadOnStartup = 1)
public class CustomerCartController extends AbstractController{

	private static final long serialVersionUID = 1L;
	private static final String MY_CART = "cart";
	
	private ProductService productService;
	private AccountService accountService;
	private PaidInfoService paidInfoService;
	
	@Override
	public void init() throws ServletException {
		productService = new ProductService(dataSource);
		accountService = new AccountService(dataSource);
		paidInfoService = new PaidInfoService(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case "/cart/add": {
			addToCart(req, resp);
			break;
		}
		case "/customer/cart/checkout": {
			// Search Address for Login Users
			LoginUser loginUser = (LoginUser) req.getSession(true).getAttribute("login");
			req.setAttribute("addresses", accountService.findAddressForCustomer(loginUser.getId()));
			forward(req, resp, "public/cart/shipping");
			break;
		}
		case "/customer/cart/payment": {
			// Get All Payments and add to Request Scope
			req.setAttribute("paidInfoList", paidInfoService.findAll());
			forward(req, resp, "public/cart/payment");
			break;
		}
		case "/customer/cart/confirm": {
			forward(req, resp, "public/cart/confirm");
			break;
		}
		default: {
			forward(req, resp, "public/cart/contents");
			break;
		}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
		
		switch (path) {
		case "/customer/cart/shipping": {
			var id = Integers.parse(req.getParameter("id"));
			PurchaseAddressForm address = null;
			if(id == 0) {
				address = new PurchaseAddressForm();
				address.setName(req.getParameter("name"));
				address.setPhone(req.getParameter("phone"));
				address.setBuilding(req.getParameter("building"));
				address.setStreet(req.getParameter("street"));
			} else {
				address = accountService.findAddressById(id);
			}
			
			cart.setAddress(address);
			resp.sendRedirect(getServletContext().getContextPath().concat("/customer/cart/payment"));
			break;
		}
		default: {
			forward(req, resp, "public/cart/contents");
			break;
		}
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