package com.i2gether.beximcom.bkashpayment.bkashpayment.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.service.PaymentService;

@Controller 
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	PaymentService paymentService;
	@GetMapping
	public String showUserList(Model model) {
		model.addAttribute("payments", paymentService.getAll());
	    return "dashboard";
	}
}
