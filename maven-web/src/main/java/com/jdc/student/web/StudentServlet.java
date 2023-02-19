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
		"/", "/student", 
		"/student-edit", "/student-details",
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
		var path = req.getServletPath();
		
		var strId = req.getParameter("id");
		var id = strId == null || strId.isEmpty() ? 0 : Integer.parseInt(strId);
		
		var jsp = switch (path) {
		case "/student-edit", "/student-details": {
			
			if(id > 0) {
				req.setAttribute("student", model.findById(id));
			}
			
			yield "%s.jsp".formatted(path);
		}
		default: {
			
			if(id > 0 && "/student-delete".equals(path)) {
				model.delete(id);
			}
			
			req.setAttribute("list", model.search(req.getParameter("name")));
			
			yield "/student-list.jsp";
		}
		};
		
		getServletContext().getRequestDispatcher(jsp)
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var strId = req.getParameter("id");
		var id = 0;
		var student = new Student();
		student.setName(req.getParameter("name"));
		student.setPhone(req.getParameter("phone"));
		student.setEmail(req.getParameter("email"));
		
		if(null == strId || strId.isEmpty()) {
			id = model.create(student);
		} else {
			id = Integer.parseInt(strId);
			model.update(id, student);
		}
		
		resp.sendRedirect(getServletContext()
				.getContextPath()
				.concat("/student-details?id=%d".formatted(id)));
	}
}
