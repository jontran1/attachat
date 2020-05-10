package com.jon.attachat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jon.attachat.entity.Comment;
import com.jon.attachat.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentSerivce;
	
	@GetMapping("/userAction/showFormCreateComment")
	public String showFormCreateComment(@RequestParam("threadId") int threadId,
			Model model) {
		
		Comment comment = new Comment();
		comment.setThreadId(threadId);
		comment.setUserName("john");
		model.addAttribute("comment", comment);
		
		return "comment-form";
	}
}