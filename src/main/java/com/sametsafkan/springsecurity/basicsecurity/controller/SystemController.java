package com.sametsafkan.springsecurity.basicsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class SystemController {

	@GetMapping("/")
	public String showSystem() {
		return "system";
	}
}
