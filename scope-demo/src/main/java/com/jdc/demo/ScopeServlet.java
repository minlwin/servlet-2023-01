package com.jdc.demo;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
		"/", "/countUp"
})
public class ScopeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final String COUNTER = "counter";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var reqCtr = (Counter)req.getAttribute(COUNTER);
		if(null == reqCtr) {
			reqCtr = new Counter();
			req.setAttribute(COUNTER, reqCtr);
		}
		reqCtr.countUp();
		
		var session = req.getSession(true);
		var sesCtr = (Counter)session.getAttribute(COUNTER);
		
		if(null == sesCtr) {
			sesCtr = new Counter();
			session.setAttribute(COUNTER, sesCtr);
		}
		sesCtr.countUp();
		
		var appCtr = (Counter)getServletContext().getAttribute(COUNTER);
		if(null == appCtr) {
			appCtr = new Counter();
			getServletContext().setAttribute(COUNTER, appCtr);
		}
		
		appCtr.countUp();
		
		getServletContext().getRequestDispatcher("/scope.jsp").forward(req, resp);
	}
}
