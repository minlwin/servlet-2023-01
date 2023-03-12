package com.jdc.shop.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import com.jdc.shop.utilities.LoginUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
		"/sign-in", "/sign-up", "/sign-out"
		}, loadOnStartup = 1)
public class SecurityController extends AbstractController{

	private static final long serialVersionUID = 1L;

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
		
		var user = new LoginUser();
		user.setLoginId(loginId);
		user.setRole(password);
		user.setName(loginId.toUpperCase());
		user.setLoginTime(LocalDateTime.now());
		
		req.getSession(true).setAttribute("login", user);
		
		resp.sendRedirect(getServletContext().getContextPath());
	}

	private void signOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		req.getSession().invalidate();
		resp.sendRedirect(getServletContext().getContextPath());
	}

	private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		// TODO implement here
	}

}