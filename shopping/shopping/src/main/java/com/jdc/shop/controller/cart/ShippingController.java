package com.jdc.shop.controller.cart;

import java.io.IOException;

import com.jdc.shop.controller.AbstractController;
import com.jdc.shop.model.dto.form.PurchaseAddressForm;
import com.jdc.shop.model.service.AddressService;
import com.jdc.shop.utilities.Integers;
import com.jdc.shop.utilities.LoginUser;
import com.jdc.shop.utilities.ShoppingCart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/customer/cart/shipping", loadOnStartup = 1)
public class ShippingController extends AbstractController{

	private static final long serialVersionUID = 1L;
	
	private AddressService service;
	
	@Override
	public void init() throws ServletException {
		service = new AddressService(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("main", "cart");

		LoginUser login = (LoginUser) req.getSession().getAttribute("login");
		req.setAttribute("addresses", service.findAddressForCustomer(login.getId()));
		
		forward(req, resp, "cart/view");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var id = Integers.parse(req.getParameter("id"));
		ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
		
		PurchaseAddressForm address = null;
		
		if(id > 0) {
			address = service.findAddressById(id);
		} else {
			address = new PurchaseAddressForm();
			address.setName(req.getParameter("name"));
			address.setPhone(req.getParameter("phone"));
			address.setBuilding(req.getParameter("building"));
			address.setStreet(req.getParameter("street"));
		}
		cart.setAddress(address);
		
		redirect(resp, "/customer/cart/shipping");
	}

}
