package com.bootcamp.exchangeshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.bootcamp.exchangeshop.services.ProductService;
import com.bootcamp.exchangeshop.services.PurchaseService;
import com.bootcamp.exchangeshop.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PurchaseController {

	private final UserService userService;
	private final ProductService prodService;
	private final PurchaseService purchaseService;

	
	public PurchaseController(ProductService prodService, UserService userService, PurchaseService purchaseService) {
		this.prodService = prodService;
		this.userService = userService;
		this.purchaseService = purchaseService;
	}
}
