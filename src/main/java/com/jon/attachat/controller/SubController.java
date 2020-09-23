package com.jon.attachat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	
	/*
	 * All string inputs will being routed into this controller
	 * have all all white spaces trimmed.
	 */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	/**
	 * Grabs all the threads pertaining to the sub name and 
	 * displays the subs in sub.jsp. 
	 * 
	 * @param subName
	 * @param model
	 * @param request
	 * @return sub.jsp
	 */
	@GetMapping("/showSub")
	public String showSub(@RequestParam("subName") String subName, Model model,
			HttpServletRequest request) {
		
		List<Thread> threads = threadService.getSubThreads(subName);
		
		/**
		 * Checks if the user is logged in and is a follower of the
		 * sub. Depending on whether the user is a follower of not 
		 * the button follow and unfollow will be displayed in sub.jsp.
		 */
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
	
	@PostMapping("/search")
	public String searchSub(@RequestParam("thread") String thread,
			@RequestParam("subName") String subName, Model model,
			HttpServletRequest request) {
		System.out.println("inside search sub: " + thread + " " + subName);
		List<Thread> threads = threadService.searchThread(thread);
		
		/**
		 * Checks if the user is logged in and is a follower of the
		 * sub. Depending on whether the user is a follower of not 
		 * the button follow and unfollow will be displayed in sub.jsp.
		 */
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
	
	/**
	 * A new sub object is created an passed into
	 * sub-form.jsp. 
	 * @param model
	 * @return sub-form.jsp
	 */
	@GetMapping("/userAction/showFormCreateSub")
	public String showFormCreateSub(Model model) {
		Sub sub = new Sub();
		
		model.addAttribute("sub", sub);
		
		return "sub-form";
	}
	
	/**
	 * This post mapping request will save the sub to the relational 
	 * database. If the sub already exist an exception will be thrown and 
	 * handled. 
	 * 
	 * @param sub
	 * @param bindingResult
	 * @param request
	 * @return sub-form or redirected.
	 */
	@PostMapping("/userAction/saveSub")
	public String saveSub(@Valid @ModelAttribute("sub") Sub sub, 
			BindingResult bindingResult,
			HttpServletRequest request) {
		
		if(bindingResult.hasErrors()) {
			return "sub-form";
		}
		
		/**
		 * A try catch statement to catch and handle DataIntregrity Violations.
		 */
		try {
			String userName = request.getUserPrincipal().getName();
			
			sub.setCreator(userName);
			sub.setNumberOfFollowers(1);
			
			subService.saveSub(sub);
		}catch(DataIntegrityViolationException e) {
			bindingResult.rejectValue("subName","error.sub", "This sub already exist.");
			e.printStackTrace();
			return "sub-form";
		}

		
		return "redirect:/";
	}
	
	/**
	 * Add user to sub as a follower. 
	 * This function grabs the user, and sub and 
	 * creates a new SubFollower object which is then
	 * saved to the database.
	 * @param subName
	 * @param redirectAttributes
	 * @param request
	 * @return redirected to showSub.
	 */
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
	
	/**
	 * SubFollowerId is used to retrieve the subfollower object.
	 * The subfollower is then removed from the database. 
	 * Decreases population count for sub.
	 * @param subName
	 * @param redirectAttributes
	 * @param request
	 * @return redirected to showSub.
	 */
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
