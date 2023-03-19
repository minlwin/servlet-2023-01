package com.jdc.shop.utilities;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.form.AccountForm;
import com.jdc.shop.model.service.AccountService;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class OwnerInitializer implements ServletContextListener{

	@Resource(name = "jdbc/shopAppDB")
	private DataSource dataSource;
	
	private AccountService service;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		service = new AccountService(dataSource);
		
		var list = service.search("Owner", null);
		
		if(list.isEmpty()) {
			// No Owner User
			var owner = new AccountForm();
			owner.setName("Min Lwin");
			owner.setEmail("minlwin@gmail.com");
			owner.setRole("Owner");
			owner.setPhone("09782003098");
			
			service.create(owner, "minlwin");
		}

	}
}
