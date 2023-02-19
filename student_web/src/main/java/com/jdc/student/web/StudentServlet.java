package com.jdc.student.web;

import java.io.IOException;

import com.jdc.student.model.Student;
import com.jdc.student.model.StudentModel;
import com.jdc.student.model.impl.StudentModelImpl;

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
		
		var idString = req.getParameter("id");
		int id = (null != idString && !idString.isEmpty()) ? Integer.parseInt(idString) : 0;
		
		var jsp = switch (servletPath) {
		case "/student-edit":
		case "/student-details":
		{
			// Add New or Edit Operation
			if(id > 0) {
				req.setAttribute("student", model.findById(id));
			}
			
			yield "%s.jsp".formatted(servletPath);
		}
		default:
			
			// Delete Operation
			if(id > 0 && "/student-delete".equals(servletPath)) {
				model.delete(id);
			}
			
			// Search
			var searchName = req.getParameter("searchName");
			var list = model.search(searchName);
			req.setAttribute("students", list);
			
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
