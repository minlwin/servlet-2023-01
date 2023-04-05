package com.jdc.shop.controller;

import java.io.IOException;

import com.jdc.shop.model.service.OrderService;
import com.jdc.shop.utilities.Integers;
import com.jdc.shop.utilities.LoginUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/members/order", loadOnStartup = 1)
public class MemberOrderController extends AbstractController{

	private static final long serialVersionUID = 1L;
	
	private OrderService orderService;
	
	@Override
	public void init() throws ServletException {
		orderService = new OrderService(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var id = Integers.parse(req.getParameter("id"));
		if(id > 0) {
			showOrderDetails(req, resp, id);
			return;
		} 
		
		// Search
		searchOrders(req, resp);
	}

	private void searchOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var login = (LoginUser) req.getSession().getAttribute("login");
		var status = req.getParameter("status");
		var dateFrom = req.getParameter("from");
		
		var list = orderService.search(login, status, dateFrom);
		req.setAttribute("list", list);
		
		forward(req, resp, "order/list");
	}

	private void showOrderDetails(HttpServletRequest req, HttpServletResponse resp, int id) throws ServletException, IOException {

		req.setAttribute("dto", orderService.findById(id));
		forward(req, resp, "order/details");
	}
	
	
}