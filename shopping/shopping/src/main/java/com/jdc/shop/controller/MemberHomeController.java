package com.jdc.shop.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(
		urlPatterns = {"/member"},
		loadOnStartup = 1
)
public class MemberHomeController extends HttpServlet{

	private static final long serialVersionUID = 1L;


}