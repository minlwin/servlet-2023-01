package com.jdc.student.web;

import java.io.IOException;

import com.jdc.student.model.Student;
import com.jdc.student.model.StudentModel;
import com.jdc.student.model.impl.StudentModelImpl;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
		"/students",
		"/student-details",
		"/student-edit",
		"/student-delete"
})
public class StudentServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private StudentModel model;
	
	@Override
	public void init() throws ServletException {
		model = new StudentModelImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var servletPath = req.getServletPath();
		
		var jsp = switch (servletPath) {
		case "/student-edit": {
			// Add New or Edit Operation
			
			yield "/student-edit.jsp";
		}
		case "/student-details": {
			// Show Details Operation
			
			yield "/student-details.jsp";
		}
		case "/student-delete": {
			// Delete Operation
			yield "/index.jsp";
		}
		default:
			// Search
			var searchName = req.getParameter("searchName");
			var list = model.search(searchName);
			req.setAttribute("list", list);
			yield "/index.jsp";
		};
		
		getServletContext().getRequestDispatcher(jsp).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Save Action
		var id = req.getParameter("id");
		var student = new Student();
		student.setName(req.getParameter("name"));
		student.setPhone(req.getParameter("phone"));
		student.setEmail(req.getParameter("email"));
		
		if(null == id || id.isEmpty()) {
			id = String.valueOf(model.create(student));
		} else {
			model.update(Integer.parseInt(id), student);
		}
		
		resp.sendRedirect(getServletContext().getContextPath()
				.concat("/student-details?id=%s".formatted(id)));
	}
}
