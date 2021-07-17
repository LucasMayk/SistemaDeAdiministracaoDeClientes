package com.tempus.administracaodecliente.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	// Mapeamento da URL - GET.
	@GetMapping
	public ModelAndView novo() {
		return new ModelAndView("dashboard/DashboardCliente");
	}
}
