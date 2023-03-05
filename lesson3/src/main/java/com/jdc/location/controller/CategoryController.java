package com.jdc.location.controller;

import java.io.IOException;

import com.jdc.location.model.CategoryModel;
import com.jdc.location.model.form.CategoryFrom;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
		"/category", "/category-edit"
})
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
		
		if(req.getServletPath().equals("/category-edit")) {
			getServletContext().getRequestDispatcher("/category/edit.jsp").forward(req, resp);
		} else {
			req.setAttribute("list", model.findAll());
			getServletContext().getRequestDispatcher("/category/list.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		var form = new CategoryFrom(req.getParameter("name"), req.getParameter("burmese"));
		
		var id = req.getParameter("id");
		if(null != id && !id.isBlank()) {
			form = form.withId(Integer.parseInt(id));
		}
		
		model.save(form);
		
		resp.sendRedirect(getServletContext().getContextPath().concat("/category"));
	}

}
