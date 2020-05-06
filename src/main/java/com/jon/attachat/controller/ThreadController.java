package com.jon.attachat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jon.attachat.service.SubService;
import com.jon.attachat.service.ThreadService;
import com.jon.attachat.service.UserService;
import com.jon.attachat.entity.Thread;

@Controller
@RequestMapping("/attaThread")
public class ThreadController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubService subService;
	
	@Autowired
	private ThreadService threadService;
	
	@GetMapping("/showAttaThread")
	public String showThread(@RequestParam("threadId") int threadId, Model model) {
		
		Thread thread = threadService.getThread(threadId);
		System.out.println(thread);
		
		model.addAttribute("thread", thread);
		
		return "thread";
	}
	
	@GetMapping("/userAction/showFormCreateAttaThread")
	public String showFormCreateThread(Model model) {
		Thread thread = new Thread();
		
		model.addAttribute("thread", thread);
		
		return "thread-form";
	}
	
	@PostMapping("/userAction/saveAttaThread")
	public String saveThread(@ModelAttribute("thread") Thread thread) {
		thread.setSubName("gaming");
		thread.setUserName("john");
		
		System.out.println("saveThread function :");
		System.out.println(thread.toString());
		threadService.saveThread(thread);
		
		return "redirect:/";
	}


}