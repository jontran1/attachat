package com.jon.attachat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jon.attachat.entity.Sub;
import com.jon.attachat.entity.Thread;
import com.jon.attachat.service.SubService;
import com.jon.attachat.service.ThreadService;
import com.jon.attachat.service.UserService;

@Controller
@RequestMapping("/attaSub")
public class SubController {
	
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
		model.addAttribute("subName", subName);
		
		return "sub";
	}
	
	@GetMapping("/userAction/showFormCreateAttaSub")
	public String showFormCreateSub(Model model) {
		Sub sub = new Sub();
		
		model.addAttribute("sub", sub);
		
		return "sub-form";
	}
	
	@PostMapping("/userAction/saveAttaSub")
	public String saveSub(@ModelAttribute("sub") Sub sub) {
		
		sub.setCreator("john");
		sub.setNumberOfFollowers(1);
		
		subService.saveSub(sub);
		
		return "redirect:/";
	}

}
