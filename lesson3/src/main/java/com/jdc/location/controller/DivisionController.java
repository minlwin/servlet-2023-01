package com.jdc.location.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.jdc.location.model.CategoryModel;
import com.jdc.location.model.DivisionModel;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
		"/division", "/division-edit"
})
public class DivisionController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/lesson3DB")
	private DataSource dataSource;
	
	private DivisionModel dModel;
	private CategoryModel cModel;
	
	@Override
	public void init() throws ServletException {
		dModel = new DivisionModel(dataSource);
		cModel = new CategoryModel(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if("/division-edit".equals(req.getServletPath())) {
			req.setAttribute("categories", cModel.findAll());
			getServletContext().getRequestDispatcher("/division/edit.jsp").forward(req, resp);
		} else {
			req.setAttribute("list", dModel.findAll());
			getServletContext().getRequestDispatcher("/division/list.jsp").forward(req, resp);
		}
	}

}
