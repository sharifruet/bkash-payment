package com.i2gether.beximcom.bkashpayment.bkashpayment.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
@RequestMapping("/dashboard")
public class DashboardController {
	@GetMapping
	public String showUserList(Model model) {
	    return "dashboard";
	}
}