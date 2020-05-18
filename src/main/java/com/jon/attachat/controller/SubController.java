package com.jon.attachat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jon.attachat.entity.Sub;
import com.jon.attachat.entity.SubFollower;
import com.jon.attachat.entity.Thread;
import com.jon.attachat.entity.User;
import com.jon.attachat.service.SubService;
import com.jon.attachat.service.ThreadService;
import com.jon.attachat.service.UserService;

@Controller
@RequestMapping("/Sub")
public class SubController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubService subService;
	
	@Autowired
	private ThreadService threadService;
	
	@GetMapping("/showSub")
	public String showSub(@RequestParam("subName") String subName, Model model) {
		
		System.out.println("test sub name: " + subName);
		List<Thread> threads = threadService.getSubThreads(subName);
		
		model.addAttribute("threads", threads);
		model.addAttribute("subName", subName);
		
		return "sub";
	}
	
	@GetMapping("/userAction/showFormCreateSub")
	public String showFormCreateSub(Model model) {
		Sub sub = new Sub();
		
		model.addAttribute("sub", sub);
		
		return "sub-form";
	}
	
	@PostMapping("/userAction/saveSub")
	public String saveSub(@ModelAttribute("sub") Sub sub, HttpServletRequest request) {
		
		String userName = request.getUserPrincipal().getName();
		
		sub.setCreator(userName);
		sub.setNumberOfFollowers(1);
		
		subService.saveSub(sub);
		
		return "redirect:/";
	}
	
	@PostMapping("/userAction/followSub")
	public String followSub(@RequestParam("subName") String subName,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		
		User user = userService.getUser(request.getUserPrincipal().getName());
		Sub sub = subService.getSub(subName);
		SubFollower subFollower = new SubFollower(user, sub);
		
		subService.addFollower(subFollower);
		
		redirectAttributes.addAttribute("subName", subName);

		return "redirect:/Sub/showSub";
	
	}

}
