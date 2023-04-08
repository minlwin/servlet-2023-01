package com.jdc.shop.controller;

import java.io.IOException;

import com.jdc.shop.model.service.AccountService;
import com.jdc.shop.model.service.OrderDeliveryService;
import com.jdc.shop.model.service.OrderMessageService;
import com.jdc.shop.model.service.OrderService;
import com.jdc.shop.utilities.DateTimes;
import com.jdc.shop.utilities.Integers;
import com.jdc.shop.utilities.LoginUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
		"/members/order",
		"/members/order/delivery",
		"/members/order/status",
		"/members/order/message"
}, loadOnStartup = 1)
public class MemberOrderController extends AbstractController{

	private static final long serialVersionUID = 1L;
	
	private OrderService orderService;
	private OrderMessageService messageService;
	private AccountService accountService;
	private OrderDeliveryService deliveryService;
	
	@Override
	public void init() throws ServletException {
		orderService = new OrderService(dataSource);
		messageService = new OrderMessageService(dataSource);
		accountService = new AccountService(dataSource);
		deliveryService = new OrderDeliveryService(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("main", "order");
		
		var id = Integers.parse(req.getParameter("id"));
		if(id > 0) {
			showOrderDetails(req, resp, id);
			return;
		} 
		
		// Search
		searchOrders(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var id = Integers.parse(req.getParameter("id"));
		
		if("/members/order/delivery".equals(req.getServletPath())) {
			requestDelivery(req, resp, id);
		} else if("/members/order/status".equals(req.getServletPath())) {
			updateStatus(req, resp, id);
		} else {
			addMessage(req, resp, id);
		}
		
		redirect(resp, "/members/order?id=%s".formatted(id));
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
		
		if("/members/order/delivery".equals(req.getServletPath())) {
			var deliveryProviders = accountService.search("Delivery", null);
			req.setAttribute("deliveryProviders", deliveryProviders);
		}
		
		forward(req, resp, "order/details");
	}
	
	private void addMessage(HttpServletRequest req, HttpServletResponse resp, int id) {
		var login = (LoginUser)req.getSession().getAttribute("login");
		var message = req.getParameter("message");
		messageService.send(id, login.getId(), message);
	}

	private void requestDelivery(HttpServletRequest req, HttpServletResponse resp, int id) {
		var delivery = Integers.parse(req.getParameter("delivery"));
		var dateFrom = DateTimes.parseDate(req.getParameter("dateFrom"));
		var dateTo = DateTimes.parseDate(req.getParameter("dateTo"));
		
		deliveryService.request(id, delivery, dateFrom, dateTo);
	}

	private void updateStatus(HttpServletRequest req, HttpServletResponse resp, int id) {
		var status = req.getParameter("status");
		var remark = req.getParameter("remark");
		
		orderService.updateStatus(id, status, remark);
	}
}