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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jon.attachat.entity.Comment;
import com.jon.attachat.service.CommentService;

@Controller
@RequestMapping("/Comment")
public class CommentController {
	
	@Autowired
	private CommentService commentSerivce;
	
	@GetMapping("/userAction/showFormCreateComment")
	public String showFormCreateComment(@RequestParam("threadId") int threadId,
			Model model) {
		
		Comment comment = new Comment();
		comment.setThreadId(threadId);
		comment.setUserName("john");
		comment.setParentId(null);
		
		model.addAttribute("comment", comment);
		
		return "comment-form";
	}
	
	@PostMapping("/userAction/saveComment")
	public String saveComment(@ModelAttribute("comment") Comment comment,
			RedirectAttributes redirectAttributes) {
		
		commentSerivce.saveComment(comment);
		redirectAttributes.addAttribute("threadId", comment.getThreadId());
		
		return "redirect:/Thread/showThread";
	}
	
	@GetMapping("/userAction/showFormCreateReply")
	public String showFormCreateComment(@RequestParam("threadId") int threadId,
			@RequestParam("parentId") int parentId,
			Model model) {
		
		Comment comment = new Comment();
		comment.setThreadId(threadId);
		comment.setUserName("john");
		comment.setParentId(parentId);
		
		model.addAttribute("comment", comment);
		model.addAttribute("isReply", true);

		return "comment-form";
	}
	
	@GetMapping("/userAction/deleteComment")
	public String deleteComment(@ModelAttribute("commentId") int commentId,
			RedirectAttributes redirectAttributes) {
		
		Comment comment = commentSerivce.getComment(commentId);
				
		commentSerivce.updateCommentContent(comment, "Deleted");
		redirectAttributes.addAttribute("threadId", comment.getThreadId());
		
		return "redirect:/Thread/showThread";
		
	}
	
}
