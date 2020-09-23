package com.jon.attachat.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jon.attachat.dao.AuthoritieDAO;
import com.jon.attachat.dao.UserDAO;
import com.jon.attachat.entity.Authoritie;
import com.jon.attachat.entity.Sub;
import com.jon.attachat.entity.SubFollower;
import com.jon.attachat.entity.User;
import com.jon.attachat.service.SubService;
import com.jon.attachat.service.ThreadService;
import com.jon.attachat.service.UserService;
import com.jon.attachat.entity.Thread;

@Controller
public class AttaChatMainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubService subService;
	
	@Autowired
	private ThreadService threadService;
	
	@GetMapping("/")
	public String showHome(Model model, HttpServletRequest request) {
		
		List<Sub> subs = subService.getSubs();
		List<Sub> userSubs = null;
		
		/**
		 * If the user is logged in, this will retreive all subs followed by the user.
		 */
		if(request.getUserPrincipal() != null) {
			userSubs = subService.getSubsByUser(request.getUserPrincipal().getName());
		}
		
		model.addAttribute("userSubs", userSubs);
		model.addAttribute("subs", subs);
		
		System.out.println("users subs " + userSubs);
		
		return "home";
	}
	
	@PostMapping("/search")
	public String showSub(@RequestParam("sub") String sub, Model model, 
			HttpServletRequest request) {
		
		System.out.println("in side search in main controller");
		List<Sub> subs = subService.searchSub(sub);
		List<Sub> userSubs = null;
		
		/**
		 * If the user is logged in, this will retreive all subs followed by the user.
		 */
		if(request.getUserPrincipal() != null) {
			userSubs = subService.getSubsByUser(request.getUserPrincipal().getName());
		}
		
		model.addAttribute("userSubs", userSubs);
		model.addAttribute("subs", subs);
		
		System.out.println("users subs " + userSubs);
		
		return "home";
	}

}
