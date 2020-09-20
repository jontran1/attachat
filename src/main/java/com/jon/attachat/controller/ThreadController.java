package com.jon.attachat.controller;

import java.util.List;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList; 
import java.util.Queue; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jon.attachat.service.CommentService;
import com.jon.attachat.service.SubService;
import com.jon.attachat.service.ThreadService;
import com.jon.attachat.service.UserService;
import com.jon.attachat.entity.Comment;
import com.jon.attachat.entity.Sub;
import com.jon.attachat.entity.SubFollowerId;
import com.jon.attachat.entity.Thread;
import com.jon.attachat.entity.User;

@Controller
@RequestMapping("/Thread")
public class ThreadController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubService subService;
	
	@Autowired
	private ThreadService threadService;
	
	@Autowired
	private CommentService commentSerivce;
	
	@GetMapping("/showThread")
	public String showThread(@RequestParam("threadId") int threadId, Model model,
			HttpServletRequest request) {
		
		Thread thread = threadService.getThread(threadId);
		List<Comment> comments = commentSerivce.getThreadParentComments(threadId);

		/*
		 * Indent comments.
		 */
		for(Comment comment : comments) {
			comment.indentReplies();
		}
		
		if(request.getUserPrincipal() != null) {
			String userName = request.getUserPrincipal().getName();
			model.addAttribute("userName", userName);			
		}
		
		/**
		 * Checks if the user is logged in and is a follower of the
		 * sub. Depending on whether the user is a follower of not 
		 * the button follow and unfollow will be displayed.
		 */
		if(request.getUserPrincipal() != null) {
			User user = userService.getUser(request.getUserPrincipal().getName());
			Sub sub = subService.getSub(thread.getSubName());
			boolean isFollower = subService.isFollower(new SubFollowerId(user, sub));
			model.addAttribute("isFollower", isFollower);
		}
		
		String userName = null;
		if(request.getUserPrincipal() != null)
			userName = request.getUserPrincipal().getName();
		
		/**
		 * A new comment object for when the user posts a comment.
		 */
		Comment comment = new Comment(threadId, userName, null);
		model.addAttribute("comment", comment);
		
		model.addAttribute("comments", comments);
		model.addAttribute("thread", thread);
	
		
		return "thread";
	}
	
	@GetMapping("/userAction/showFormCreateThread")
	public String showFormCreateThread(@RequestParam("subName") String subName, Model model, HttpServletRequest request) {
		Thread thread = new Thread();
		
		model.addAttribute("thread", thread);
		String userName = request.getUserPrincipal().getName();
		
		thread.setSubName(subName);
		thread.setUserName(userName);
		thread.setLocalDateTime(LocalDateTime.now());
		
		return "thread-form";
	}
	
	@GetMapping("/userAction/editFormThread")
	public String showFormEditThread(@RequestParam("threadId") int threadId, Model mode,
			HttpServletResponse response, HttpServletRequest request) {
			
		Thread thread = null;

		/*
		 * If the user's user name matches the thread user name. Then that user can edit the thread.
		 * If not, a response error will be thrown. 
		 */
		try {
			thread = threadService.getThread(threadId);
						
			if(thread == null || !request.getUserPrincipal().getName().equals(thread.getUserName())) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden access");
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mode.addAttribute("thread", thread);
				
		return "thread-form";
	}
	
	@PostMapping("/userAction/saveThread")
	public String saveThread(@ModelAttribute("thread") Thread thread, 
			RedirectAttributes redirectAttributes) {	
						
		threadService.saveOrUpdateThread(thread);		
		
		redirectAttributes.addAttribute("threadId", thread.getThreadId());

		return "redirect:/Thread/showThread";
	}


}
