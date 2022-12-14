package com.canwia.askquestion.controller;

import com.canwia.askquestion.model.Post;
import com.canwia.askquestion.request.PostCreateRequest;
import com.canwia.askquestion.request.PostUpdateRequest;
import com.canwia.askquestion.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts/v1")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public List<Post> getAllPost(@RequestParam Optional<Long> userId){
        return postService.getAllPost(userId);
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest postCreateRequest){
        return postService.createPost(postCreateRequest);
    }

    @PutMapping("/{postId}")
    public Post updatePostById(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePostRequest){
        return postService.updatePostById(postId,updatePostRequest);
    }

    @DeleteMapping("/{postId}")
    public void deletePostById(@PathVariable Long postId){
        postService.deletePostId(postId);
    }
}
