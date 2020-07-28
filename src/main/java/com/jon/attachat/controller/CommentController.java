package com.jon.attachat.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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

import com.jon.attachat.entity.Comment;
import com.jon.attachat.service.CommentService;

@Controller
@RequestMapping("/Comment")
public class CommentController {
	
	@Autowired
	private CommentService commentSerivce;
	
	@GetMapping("/userAction/showFormCreateComment")
	public String showFormCreateComment(@RequestParam("threadId") int threadId,
			HttpServletRequest request,
			Model model) {
		
		String userName = request.getUserPrincipal().getName();
		
		Comment comment = new Comment(threadId, userName, null);
		
		model.addAttribute("comment", comment);
		
		return "comment-form";
	}
	
	@GetMapping("/userAction/showFormEditComment")
	public String showFormEditComment(@RequestParam("commentId") int commentId, 
			Model model, HttpServletResponse response, HttpServletRequest request) {
		
		Comment comment = null;
		
		/*
		 * Checks if the user currently logged in is the comment creator.
		 * Only comment creators can edit their own comments.
		 */
		try {
			
			comment = commentSerivce.getComment(commentId);
			
			if(comment == null || !request.getUserPrincipal().getName().equals(comment.getUserName()) || comment.getDeleted()) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden access");
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(comment.getParentId() != null) model.addAttribute("isReply", true);
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
			RedirectAttributes redirectAttributes) {
		
		if(comment.getLocalDateTime() == null)
			comment.setLocalDateTime(LocalDateTime.now());
		
		/*
		 * Used saveOrUpdate because if the user edits the comment. That means the comment already exist.
		 * So the comment will be updated.
		 * If the user creates a new comment. Then a new row will be inserted into the comment table.
		 */
		commentSerivce.saveOrUpdate(comment);
		redirectAttributes.addAttribute("threadId", comment.getThreadId());
		
		return "redirect:/Thread/showThread";
	}
	
	@GetMapping("/userAction/showFormCreateReply")
	public String showFormCreateComment(@RequestParam("threadId") int threadId,
			@RequestParam("parentId") int parentId,
			HttpServletRequest request,
			Model model) {
		
		String userName = request.getUserPrincipal().getName();
		
		Comment comment = new Comment(threadId, userName, null, parentId);
		
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
