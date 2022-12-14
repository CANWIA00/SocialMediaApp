package com.canwia.askquestion.repository;

import com.canwia.askquestion.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findByUserIdAndPostId(Long postId,Long userId);

    List<Like> findByUserId(Long userId);

    List<Like> findByPostId(Long postId);
}
