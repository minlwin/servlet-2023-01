package com.jdc.shop.controller;

import java.io.IOException;
import java.util.List;

import com.jdc.shop.model.dto.form.AccountForm;
import com.jdc.shop.model.dto.page.PaginationPageResultAdapter;
import com.jdc.shop.model.service.AccountService;
import com.jdc.shop.utilities.Integers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/owner/members", "/owner/members/details", "/owner/members/edit" }, loadOnStartup = 1)
public class OwnerMemberController extends AbstractController {

	private static final long serialVersionUID = 1L;
	private static List<Integer> PAGE_SIZES = List.of(10, 15, 25);
	
	private AccountService service;
	
	@Override
	public void init() throws ServletException {
		service = new AccountService(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		
		req.setAttribute("main", "members");
		
		int id = Integers.parse(req.getParameter("id"));
		if(id > 0) {
			var account = service.findById(id);
			req.setAttribute("dto", account);
		}
		
		if("/owner/members/details".equals(path)) {
			showDetails(req, resp);
		} else if ("/owner/members/edit".equals(path)) {
			edit(req, resp);
		} else {
			search(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var form = new AccountForm();
		form.setId(Integers.parse(req.getParameter("id")));
		form.setName(req.getParameter("name"));
		form.setEmail(req.getParameter("email"));
		form.setRole(req.getParameter("role"));
		form.setPhone(req.getParameter("phone"));
		
		int id = service.save(form);
		redirect(resp, "/owner/members/details?id=%d".formatted(id));
	}

	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var currentPage = Integers.parse(req.getParameter("page"));
		
		if(currentPage == 0) {
			currentPage = 1;
		}
		
		var pageSize = Integers.parse(req.getParameter("size"));
		
		if(pageSize == 0) {
			pageSize = PAGE_SIZES.get(0);
		}
		req.setAttribute("pageSizes", PAGE_SIZES);
		
		var role = req.getParameter("role");
		var keyword = req.getParameter("keyword");
		
		var result = service.search(role, keyword, currentPage, pageSize);
		req.setAttribute("model", new PaginationPageResultAdapter<>(result));
		
		forward(req, resp, "/members/list");
	}

	private void showDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forward(req, resp, "/members/details");
	}

	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forward(req, resp, "/members/edit");
	}

}