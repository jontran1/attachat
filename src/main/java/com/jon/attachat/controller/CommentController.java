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
		comment.setParentId(null);
		comment.setDeleted(false);
		
		model.addAttribute("comment", comment);
		
		return "comment-form";
	}
	
	@GetMapping("/userAction/showFormEditComment")
	public String showFormEditComment(@RequestParam("commentId") int commentId,
			Model model) {
		
		Comment comment = commentSerivce.getComment(commentId);
		model.addAttribute("comment", comment);
		
		return "comment-form";
	}
	
	@GetMapping("/user/showComments")
	public String showUserComment(@RequestParam("userName") String userName,
			Model model) {
		List<Comment> userComments = commentSerivce.getUserComment(userName);
		
		model.addAttribute("comments", userComments);
		model.addAttribute("userName", userName);
		System.out.println(userComments);
		
		return "comment-history";
	}
	
	@PostMapping("/userAction/saveComment")
	public String saveComment(@ModelAttribute("comment") Comment comment,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		String userName = request.getUserPrincipal().getName();
		comment.setUserName(userName);
		
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
		comment.setParentId(parentId);
		comment.setDeleted(false);
		
		model.addAttribute("comment", comment);
		model.addAttribute("isReply", true);

		return "comment-form";
	}
	
	@PostMapping("/userAction/deleteComment")
	public String deleteComment(@ModelAttribute("commentId") int commentId,
			RedirectAttributes redirectAttributes) {
		
		Comment comment = commentSerivce.getComment(commentId);
		comment.setDeleted(true);
		comment.setContent("Deleted");
		commentSerivce.updateComment(comment);
		redirectAttributes.addAttribute("threadId", comment.getThreadId());
		
		return "redirect:/Thread/showThread";
		
	}
	
}
