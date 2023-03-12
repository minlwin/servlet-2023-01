package com.jdc.shop.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class AbstractController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String view) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/pages/%s.jsp".formatted(view)).forward(req, resp);
	}

}
