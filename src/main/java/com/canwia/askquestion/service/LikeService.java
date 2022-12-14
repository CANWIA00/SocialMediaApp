package com.canwia.askquestion.service;

import com.canwia.askquestion.model.Like;
import com.canwia.askquestion.model.Post;
import com.canwia.askquestion.model.User;
import com.canwia.askquestion.repository.LikeRepository;
import com.canwia.askquestion.request.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Like> getAllLike(Optional<Long> postId, Optional<Long> userId) {
        if(userId.isPresent()&&postId.isPresent()){
            return likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findByPostId(postId.get());
        }else {
            return likeRepository.findAll();
        }
    }

    public Like getLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(new Like());
    }

    public Like createLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.getUserById(likeCreateRequest.getUserId());
        Post post = postService.getPostById(likeCreateRequest.getPostId());

        if(user != null && post != null){
            Like like = new Like();
            like.setId(likeCreateRequest.getId());
            like.setPost(post);
            like.setUser(user);
            return likeRepository.save(like);
        }else {
            return new Like();
        }
    }

    public void deleteLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
