package com.jdc.shop.controller;

import java.io.IOException;

import com.jdc.shop.model.dto.form.CategoryForm;
import com.jdc.shop.model.service.CategoryService;
import com.jdc.shop.utilities.Integers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/sale/category", loadOnStartup = 1)
public class SaleCategoryController extends AbstractController{

	private static final long serialVersionUID = 1L;
	
	private CategoryService service;
	
	@Override
	public void init() throws ServletException {
		service = new CategoryService(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var list = service.findAll();
		req.setAttribute("list", list);
		forward(req, resp, "/paid/list");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var form = new CategoryForm();
		form.setId(Integers.parse(req.getParameter("id")));
		form.setName(req.getParameter("name"));
		
		service.save(form);
		
		resp.sendRedirect(getServletContext().getContextPath().concat("/sale/category"));
	}

}