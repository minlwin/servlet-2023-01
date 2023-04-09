package com.jdc.shop.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.jdc.shop.utilities.BusinessException;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class AbstractController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/shopAppDB")
	protected DataSource dataSource;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			super.service(req, resp);
		} catch (BusinessException e) {
			redirect(resp, "/public?error=%s".formatted(e.getMessage()));
		}
	}
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String view) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/pages/%s.jsp".formatted(view)).forward(req, resp);
	}

	protected void redirect(HttpServletResponse resp, String path) throws IOException {
		resp.sendRedirect(getServletContext().getContextPath().concat(path));
	}
	
	protected String getImageFolder() {
		return getServletContext().getRealPath("/resources/photos");
	}
}
