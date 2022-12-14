package com.canwia.askquestion.controller;

import com.canwia.askquestion.model.Like;
import com.canwia.askquestion.request.LikeCreateRequest;
import com.canwia.askquestion.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/like/v1")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getAllLike(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId){
        return likeService.getAllLike(postId,userId);
    }

    @GetMapping("/{commentId}")
    public Like getLikeById(@PathVariable Long likeId){
        return likeService.getLikeById(likeId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest likeCreateRequest){
        return likeService.createLike(likeCreateRequest);
    }

    @DeleteMapping
    public void deleteLikeById(@PathVariable Long likeId){
        likeService.deleteLikeById(likeId);
    }

}
