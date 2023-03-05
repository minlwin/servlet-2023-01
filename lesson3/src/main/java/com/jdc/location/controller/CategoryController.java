package com.jdc.location.controller;

import java.io.IOException;

import com.jdc.location.model.CategoryModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/category")
public class CategoryController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private CategoryModel model;
	
	@Override
	public void init() throws ServletException {
		super.init();
		model = new CategoryModel();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("list", model.findAll());
		
		getServletContext().getRequestDispatcher("/category/list.jsp").forward(req, resp);
	}


}
