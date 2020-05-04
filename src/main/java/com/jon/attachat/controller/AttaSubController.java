package com.jon.attachat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jon.attachat.entity.Thread;
import com.jon.attachat.service.SubService;
import com.jon.attachat.service.ThreadService;
import com.jon.attachat.service.UserService;

@Controller
public class AttaSubController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubService subService;
	
	@Autowired
	private ThreadService threadService;
	
	@GetMapping("/showAttaSub")
	public String showSub(@RequestParam("subName") String subName, Model model) {
		
		System.out.println("test sub name: " + subName);
		List<Thread> threads = threadService.getSubThreads(subName);
		
		model.addAttribute("threads", threads);
		
		return "sub";
	}

}
