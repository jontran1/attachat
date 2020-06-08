package com.jon.attachat.controller;

import java.util.List;
import java.util.LinkedList; 
import java.util.Queue; 
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

import com.jon.attachat.service.CommentService;
import com.jon.attachat.service.SubService;
import com.jon.attachat.service.ThreadService;
import com.jon.attachat.service.UserService;
import com.jon.attachat.entity.Comment;
import com.jon.attachat.entity.Thread;

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
		List<Comment> comments = commentSerivce.getThreadComments(threadId);

		for(Comment comment : comments) {
			int indent = 0;
			Queue<Comment> queue = new LinkedList<>();
			queue.add(comment);
			comment.indent = indent;

			while(!queue.isEmpty()) {
				Comment node = queue.remove();
				indent += 10;
				for(Comment child : node.getChildren()) {
					queue.add(child);
					child.indent = indent;
				}
				
			}
		}
		if(request.getUserPrincipal() != null) {
			String userName = request.getUserPrincipal().getName();
			model.addAttribute("userName", userName);			
		}
		model.addAttribute("comments", comments);
		model.addAttribute("thread", thread);
	
		
		return "thread";
	}
	
	@GetMapping("/userAction/showFormCreateThread")
	public String showFormCreateThread(@RequestParam("subName") String subName, Model model) {
		Thread thread = new Thread();
		
		model.addAttribute("thread", thread);
		
		thread.setSubName(subName);
		thread.setUserName("john");
		
		return "thread-form";
	}
	
	@GetMapping("/userAction/editFormThread")
	public String showFormEditThread(@RequestParam("threadId") int threadId, Model mode) {
		Thread thread = threadService.getThread(threadId);
		
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
