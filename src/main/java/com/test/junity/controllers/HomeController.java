package com.test.junity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.junity.services.HomeService;

@RestController
@RequestMapping("home")
public class HomeController {
	
	@Autowired
	public HomeService service;
	
	@Value("${welcome}")
	public String env;
	
	@RequestMapping("/")
	public String healthCheck() {
		return "OK";
	}
	
	@GetMapping("/env")
	public String Welcome() {
		System.out.println("You are running on "+env+" environment");
		return "You are running on "+env+" environment";
	}
	
	@PostMapping("/add")
	public String add(int a , int b) {
		return "Sum : "+service.add(a, b);
	}
	
	@PostMapping("/sub")
	public String sub(int a , int b) {
		return "Difference : "+service.sub(a, b);
	}
}
