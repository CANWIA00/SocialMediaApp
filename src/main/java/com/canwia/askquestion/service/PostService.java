package com.canwia.askquestion.service;

import com.canwia.askquestion.model.Post;
import com.canwia.askquestion.model.User;
import com.canwia.askquestion.repository.PostRepository;
import com.canwia.askquestion.request.PostCreateRequest;
import com.canwia.askquestion.request.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {


    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPost(Optional<Long> userId) {
        if(userId.isPresent()){
            return postRepository.findByUserId(userId.get());
        }else {
            return postRepository.findAll();
        }
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(new Post());
    }


    public Post createPost(PostCreateRequest postCreateRequest) {

        User user = userService.getUserById(postCreateRequest.getUserId());

        if (user == null){
            return null;
        }else {
            Post post = new Post();
            post.setId(postCreateRequest.getId());
            post.setText(postCreateRequest.getText());
            post.setTitle(postCreateRequest.getTitle());
            post.setUser(user);
            return postRepository.save(post);
        }
    }

    public Post updatePostById(Long postId, PostUpdateRequest updatePostRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()){
            Post newPost = new Post();
            newPost.setTitle(updatePostRequest.getTitle());
            newPost.setText(updatePostRequest.getText());
            postRepository.save(newPost);
            return newPost;
        }
        return null;
    }

    public void deletePostId(Long postId) {
        postRepository.deleteById(postId);
    }


}
