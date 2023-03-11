package com.jdc.location.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.sql.DataSource;

import com.jdc.location.model.CategoryModel;
import com.jdc.location.model.DivisionModel;
import com.jdc.location.model.form.DivisionForm;
import com.jdc.location.utils.Integers;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/division", "/division-edit", "/division-upload"})
@MultipartConfig
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
			
			var id = Integers.parseInt(req.getParameter("id"));
			
			if(id > 0) {
				var form = dModel.findFormById(id);
				req.setAttribute("form", form);
			}
			
			req.setAttribute("categories", cModel.findAll());
			getServletContext().getRequestDispatcher("/division/edit.jsp").forward(req, resp);
		} else {
			req.setAttribute("list", dModel.findAll());
			getServletContext().getRequestDispatcher("/division/list.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getServletPath().equals("/division-upload")) {
			// File Upload
			var file = req.getPart("file");
			
			try(var br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
				String line = null;
				
				while(null != (line = br.readLine())) {
					var array = line.split("\t");
					var form = new DivisionForm(0, array[0], array[1], array[2], Integer.parseInt(array[3]));
					dModel.save(form);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			// Save
			var id = Integers.parseInt(req.getParameter("id"));
			var name = req.getParameter("name");
			var category = Integers.parseInt(req.getParameter("category"));
			var burmese = req.getParameter("burmese");
			var capital = req.getParameter("capital");
			
			var form = new DivisionForm(id, name, burmese, capital, category);
			dModel.save(form);
		}
		
		resp.sendRedirect(getServletContext().getContextPath().concat("/division"));
	}

}
