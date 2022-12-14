package com.canwia.askquestion.service;

import com.canwia.askquestion.model.Comment;
import com.canwia.askquestion.model.Post;
import com.canwia.askquestion.model.User;
import com.canwia.askquestion.repository.CommentRepository;
import com.canwia.askquestion.request.CommentCreateRequest;
import com.canwia.askquestion.request.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllComment(Optional<Long> userId, Optional<Long> postId) {

        if(userId.isPresent()&&postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        }else {
            return commentRepository.findAll();
        }
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(new Comment());
    }

    public Comment createComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.getUserById(commentCreateRequest.getUserId());
        Post post = postService.getPostById(commentCreateRequest.getPostId());

        if(user!= null && post != null){
            Comment comment = new Comment();
            comment.setId(commentCreateRequest.getId());
            comment.setPost(post);
            comment.setUser(user);
            comment.setText(commentCreateRequest.getText());
            return commentRepository.save(comment);
        }else {
            return new Comment();
        }
    }

    public Comment updateCommentById(Long commentId ,CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if(comment.isPresent()){
            Comment commentNew = comment.get();
            commentNew.setText(commentUpdateRequest.getText());
            return commentRepository.save(commentNew);
        }else {
            return new Comment();
        }
    }

    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
