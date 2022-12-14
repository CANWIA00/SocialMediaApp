package com.canwia.askquestion.controller;

import com.canwia.askquestion.model.Comment;
import com.canwia.askquestion.request.CommentCreateRequest;
import com.canwia.askquestion.request.CommentUpdateRequest;
import com.canwia.askquestion.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment/v1")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComment(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentService.getAllComment(userId,postId);
    }

    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId){
        return commentService.getCommentById(commentId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.createComment(commentCreateRequest);
    }

    @PutMapping("/{commentId}")
    public Comment updateCommentById(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest){
        return commentService.updateCommentById(commentId,commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteCommendById(@PathVariable Long commentId){
        commentService.deleteCommentById(commentId);
    }
}
