package com.jdc.shop.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.jdc.shop.model.service.AccountService;
import com.jdc.shop.utilities.ShoppingCart;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
		"/sign-in", "/sign-up", "/sign-out"
		}, loadOnStartup = 1)
public class SecurityController extends AbstractController{

	private static final long serialVersionUID = 1L;
	
	private AccountService service;
	
	@Resource(name = "jdbc/shopAppDB")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		service = new AccountService(dataSource);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case "/sign-in":
			signIn(req, resp);
			break;
		case "/sign-up":
			signUp(req, resp);
			break;

		default:
			signOut(req, resp);
			break;
		}
	}

	private void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		var loginId = req.getParameter("loginId");
		var password = req.getParameter("password");
		
		req.login(loginId, password);
		
		var session = req.getSession(true);
		
		var loginUser = service.findLoginUser(loginId);
		session.setAttribute("login", loginUser);
		String page = "";
		
		if(loginUser.getRole().equals("Customer")) {
			ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
			page = (null != cart && !cart.getItems().isEmpty()) ? "/cart/checkout" : "";
		}
		
		redirect(resp, page);
	}

	private void signOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		req.getSession().invalidate();
		resp.sendRedirect(getServletContext().getContextPath());
	}

	private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		var name = req.getParameter("name");
		var loginId = req.getParameter("loginId");
		var password = req.getParameter("password");
		
		service.signUp(name, loginId, password);
		
		signIn(req, resp);
	}

}