package com.jdc.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.jdc.shop.model.dto.form.ProductForm;
import com.jdc.shop.model.service.CategoryService;
import com.jdc.shop.model.service.ProductPhotoService;
import com.jdc.shop.model.service.ProductService;
import com.jdc.shop.utilities.Integers;
import com.jdc.shop.utilities.Strings;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { 
		"/sale/product", 
		"/sale/product/edit", 
		"/sale/product/details", 
		"/sale/product/soldout", 
		"/sale/product/cover", 
		"/sale/product/photo",

}, loadOnStartup = 1)
@MultipartConfig
public class SaleProductController extends AbstractController {

	private static final long serialVersionUID = 1L;

	private ProductService service;
	private CategoryService categories;
	private ProductPhotoService photoService;
	
	@Override
	public void init() throws ServletException {
		service = new ProductService(dataSource);
		categories = new CategoryService(dataSource);
		photoService = new ProductPhotoService(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("main", "master");
		req.setAttribute("sub", "product");
		
		var id = Integers.parse(req.getParameter("id"));
		var dto = id > 0 ? service.findById(id) : null;

		req.setAttribute("dto", dto);

		var page = switch (req.getServletPath()) {
		
		case "/sale/product/edit" -> {
			var all = categories.findAll();
			if(null != dto) {
				all = dto.getSelectedCategory(all);
			}
			// Categories
			req.setAttribute("categories", all);
			yield "edit";
		}
		case "/sale/product/details" -> {
			yield "details";
		}
		default -> {
			var category = req.getParameter("category");
			var keyword = req.getParameter("keyword");
			req.setAttribute("list", service.searchForAdmin(category, keyword));
			yield "list";
		}
		};

		forward(req, resp, "/product/".concat(page));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var id = Integers.parse(req.getParameter("id"));

		switch (req.getServletPath()) {
		
		case "/sale/product/photo" -> {
			// Photo Upload
			var parts = req.getParts();
			photoService.uploadPhotos(id, new ArrayList<>(parts), getImageFolder());
		}
		
		case "/sale/product/soldout" -> {
			var soldOutStr = req.getParameter("soldOut");
			var soldOut = Boolean.parseBoolean(soldOutStr);
			
			service.setSoldOut(id, soldOut);
		}

		case "/sale/product/cover" -> {
			var cover = req.getParameter("cover");
			var deleteStr = req.getParameter("delete");
			var delete = Strings.isBlanck(deleteStr) ? false : Boolean.parseBoolean(deleteStr);
			
			if(delete) {
				photoService.deleteImage(id, getImageFolder(), cover);
			} else {
				photoService.setCoverImage(id, cover);
			}
		}
		
		default -> {
			// Save Product
			var name = req.getParameter("productName");
			var brand = req.getParameter("brandName");
			var price = Integers.parse(req.getParameter("price"));
			var description = req.getParameter("description");
			var categories = req.getParameterValues("category");
			var featureNames = req.getParameterValues("featureName");
			var featureValues = req.getParameterValues("featureValue");
			
			var form = ProductForm.buidler()
					.id(id).name(name).brand(brand)
					.price(price).description(description)
					.categories(categories)
					.features(featureNames, featureValues)
					.build();
			
			id = service.save(form);
		}
		
		}
		
		resp.sendRedirect(getServletContext().getContextPath()
				.concat("/sale/product/details".concat("?id=%d".formatted(id))));

	}

}