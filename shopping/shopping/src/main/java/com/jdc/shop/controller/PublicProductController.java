package com.jdc.shop.controller;

import java.io.IOException;
import java.util.List;

import com.jdc.shop.model.dto.page.PaginationPageResultAdapter;
import com.jdc.shop.model.service.ProductService;
import com.jdc.shop.utilities.Integers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = "/products",
		loadOnStartup = 1
)
public class PublicProductController extends AbstractController{

	private static final long serialVersionUID = 1L;
	
	private static List<Integer> PAGE_SIZES = List.of(8, 12, 16);

	private ProductService service;
	
	@Override
	public void init() throws ServletException {
		service = new ProductService(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var id = Integers.parse(req.getParameter("product-id"));
		if(id == 0) {
			search(req, resp);
		} else {
			showDetails(req, resp, id);
		}
		
	}

	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get Search Params
		var currentPage = Integers.parse(req.getParameter("page"));
		
		if(currentPage == 0) {
			currentPage = 1;
		}
		
		var pageSize = Integers.parse(req.getParameter("size"));
		
		if(pageSize == 0) {
			pageSize = PAGE_SIZES.get(0);
		}
		

		var keyword = req.getParameter("keyword");
		
		// Search with service
		// set result to request scope
		req.setAttribute("pageSizes", PAGE_SIZES);
		var result = service.search(keyword, currentPage, pageSize);
		req.setAttribute("model", new PaginationPageResultAdapter<>(result));
		
		// forward to home.jsp
		forward(req, resp, "public/product-list");
	}

	private void showDetails(HttpServletRequest req, HttpServletResponse resp, int productId) throws ServletException, IOException {
		// Find Product Details info by product id
		// set result to request scope
		req.setAttribute("dto", service.findById(productId));
		
		// forward to /product/details.jsp
		forward(req, resp, "public/product-details");
	}

}