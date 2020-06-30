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
import com.jon.attachat.entity.SubFollowerId;
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
	public String showSub(@RequestParam("subName") String subName, Model model,
			HttpServletRequest request) {
		
		List<Thread> threads = threadService.getSubThreads(subName);
		
		if(request.getUserPrincipal() != null) {
			User user = userService.getUser(request.getUserPrincipal().getName());
			Sub sub = subService.getSub(subName);
			boolean isFollower = subService.isFollower(new SubFollowerId(user, sub));
			model.addAttribute("isFollower", isFollower);
		}

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
	public String saveSub(@Valid @ModelAttribute("sub") Sub sub, 
			BindingResult bindingResult,
			HttpServletRequest request) {
		
		if(bindingResult.hasErrors()) {
			return "sub-form";
		}
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
	
	@PostMapping("/userAction/unfollowSub")
	public String unfollowSub(@RequestParam("subName") String subName,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		
		User user = userService.getUser(request.getUserPrincipal().getName());
		Sub sub = subService.getSub(subName);
		SubFollowerId id = new SubFollowerId(user, sub);
		SubFollower subFollower = subService.getSubFollower(id);
		
		if(subFollower != null) {
			subService.removeFollower(subFollower);
			subService.decreaseSubPopulationCount(sub);
		}
		
		redirectAttributes.addAttribute("subName", subName);

		return "redirect:/Sub/showSub";
	}

}
