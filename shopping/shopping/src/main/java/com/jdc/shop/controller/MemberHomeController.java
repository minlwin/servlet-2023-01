package com.jdc.shop.controller;

import java.io.IOException;
import java.time.LocalDate;

import com.jdc.shop.model.dto.form.AccountForm;
import com.jdc.shop.model.dto.form.PurchaseAddressForm;
import com.jdc.shop.model.service.AccountService;
import com.jdc.shop.model.service.AddressService;
import com.jdc.shop.model.service.OrderService;
import com.jdc.shop.utilities.Integers;
import com.jdc.shop.utilities.LoginUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {
				"/members",
				"/members/profile",
				"/members/address",
				"/members/password",
		},
		loadOnStartup = 1
)
public class MemberHomeController extends AbstractController{

	private static final long serialVersionUID = 1L;
	
	private AccountService accountService;
	private AddressService addressService;
	private OrderService orderService;
	
	@Override
	public void init() throws ServletException {
		accountService = new AccountService(dataSource);
		addressService = new AddressService(dataSource);
		orderService = new OrderService(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("main", "home");
		
		var login = (LoginUser)req.getSession().getAttribute("login");
		var loginUser = accountService.findById(login.getId());
		req.setAttribute("member", loginUser);

		var orders = orderService.search(login, null, LocalDate.now().minusDays(10));
		req.setAttribute("orders", orders);
		
		if(login.getRole().equals("Customer")) {
			var addresses = addressService.findAddressForCustomer(login.getId());
			req.setAttribute("addresses", addresses);
		}
		
		forward(req, resp, "member/home");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if("/members/password".equals(req.getServletPath())) {
			changePassword(req, resp);
		} else	if("/members/address".equals(req.getServletPath())) {
			editAddress(req, resp);
		} else {
			editProfile(req, resp);
		}
		
		redirect(resp, "/members");
	}

	private void editAddress(HttpServletRequest req, HttpServletResponse resp) {
		var form = new PurchaseAddressForm();
		form.setId(Integers.parse(req.getParameter("id")));
		form.setName(req.getParameter("name"));
		form.setPhone(req.getParameter("phone"));
		form.setBuilding(req.getParameter("building"));
		form.setStreet(req.getParameter("street"));
		
		var login = (LoginUser)req.getSession().getAttribute("login");
		addressService.save(login.getId(), form);
	}

	private void editProfile(HttpServletRequest req, HttpServletResponse resp) {
		var login = (LoginUser)req.getSession().getAttribute("login");
		
		var form = new AccountForm();
		form.setId(login.getId());
		form.setRole(login.getRole());
		form.setName(req.getParameter("name"));
		form.setPhone(req.getParameter("phone"));
		
		accountService.save(form);
	}

	private void changePassword(HttpServletRequest req, HttpServletResponse resp) {
		var login = (LoginUser)req.getSession().getAttribute("login");
		var oldPass = req.getParameter("oldPass");
		var newPass = req.getParameter("newPass");
		
		accountService.changePassword(login.getId(), oldPass, newPass);
	}
}