package com.jon.attachat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jon.attachat.dao.AuthoritieDAO;
import com.jon.attachat.dao.UserDAO;
import com.jon.attachat.entity.Authoritie;
import com.jon.attachat.entity.Sub;
import com.jon.attachat.entity.User;
import com.jon.attachat.service.SubService;
import com.jon.attachat.service.UserService;

@Controller
public class AttaChatMainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubService subService;
		
	@GetMapping("/")
	public String showHome(Model model) {
		List<Sub> subs = subService.getSubs();
		
		model.addAttribute("subs", subs);
		
		return "home";
	}
	
	@GetMapping("/test")
	public String showTest() {
		return "test";
	}
}
