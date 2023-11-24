package com.vikash.instBackend.repo;

import com.vikash.instBackend.model.Like;
import com.vikash.instBackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILikeRepo extends JpaRepository<Like,Integer> {
    List<Like> findByInstaPost(Post myPost);
   // List<Like> findByInstaPost(Integer myPost);
}
