package com.jdc.shop.controller;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdc.shop.model.dto.form.PaidInfoForm;
import com.jdc.shop.model.dto.form.PaidInfoForm.Type;
import com.jdc.shop.model.service.PaidInfoService;
import com.jdc.shop.utilities.Integers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/owner/paid-info", "/owner/padi-info/details"},
		loadOnStartup = 1
)
public class OwnerPaidInfoController extends AbstractController{

	private static final long serialVersionUID = 1L;
	
	private PaidInfoService service;
	private ObjectMapper mapper;
	
	@Override
	public void init() throws ServletException {
		service = new PaidInfoService(dataSource);
		mapper = new ObjectMapper();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		
		req.setAttribute("main", "master");
		req.setAttribute("sub", "paid");
		
		if("/owner/padi-info/details".equals(path)) {
			findById(req, resp);
		} else {
			var list = service.findAll();
			req.setAttribute("list", list);
			
			forward(req, resp, "/paid/list");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Save
		var form = new PaidInfoForm();
		
		form.setId(Integers.parse(req.getParameter("id")));
		form.setPaymentType(Type.valueOf(req.getParameter("paymentType")));
		form.setPaymentName(req.getParameter("paymentName"));
		form.setAccountNumber(req.getParameter("accountNumber"));
		form.setAccountName(req.getParameter("accountName"));
		
		service.save(form);
		
		redirect(resp, "/owner/paid-info");
	}

	private void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var id = Integers.parse(req.getParameter("id"));
		if(id > 0) {
			var data = service.findById(id);
			resp.setContentType("application/json");
			resp.getOutputStream().write(mapper.writeValueAsBytes(data));
		}
	}

}