package com.jdc.shop.controller.cart;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.jdc.shop.controller.AbstractController;
import com.jdc.shop.model.dto.form.PurchasePaidForm;
import com.jdc.shop.model.service.PaidInfoService;
import com.jdc.shop.utilities.Integers;
import com.jdc.shop.utilities.ShoppingCart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet(urlPatterns = {
		"/customer/cart/payment",
		"/customer/cart/payment/delete"
}, loadOnStartup = 1)
public class PaidController extends AbstractController{

	private static final long serialVersionUID = 1L;
	
	private PaidInfoService paidInfoService;
	
	@Override
	public void init() throws ServletException {
		paidInfoService = new PaidInfoService(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if("/customer/cart/payment/delete".equals(req.getServletPath())) {
			var index = Integers.parse(req.getParameter("index"));
			ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
			cart.removePaid(index);
		}
		
		req.setAttribute("paidInfoList", paidInfoService.findAll());
		forward(req, resp, "public/cart/payment");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var paymentInfoId = req.getParameter("paymentInfoId");
		var amount = req.getParameter("amount");
		var payment = req.getParameter("payment");
		var accountNumber = req.getParameter("accountNumber");
		var accountName = req.getParameter("accountName");
		
		var screenShoot = req.getPart("screenShoot");
		var screenShootFileName = createImage(screenShoot);
		
		var paidForm = new PurchasePaidForm();
		paidForm.setPaidInfoId(Integers.parse(paymentInfoId));
		paidForm.setAmount(Integers.parse(amount));
		paidForm.setScreenShoot(screenShootFileName);
		paidForm.setPayment(payment);
		paidForm.setAccountNumber(accountNumber);
		paidForm.setAccountName(accountName);
		
		ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
		cart.addPaidInfo(paidForm);
		
		redirect(resp, "/customer/cart/payment");
	}

	private String createImage(Part screenShoot) {
		
		var fileName = "%s-%s".formatted(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) ,screenShoot.getSubmittedFileName());
		var distination = Path.of(getImageFolder(), fileName);
		
		try {
			Files.copy(screenShoot.getInputStream(), distination, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}

}
