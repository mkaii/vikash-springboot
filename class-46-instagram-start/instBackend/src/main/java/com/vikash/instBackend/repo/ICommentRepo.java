package com.vikash.instBackend.repo;

import com.vikash.instBackend.model.Comment;
import com.vikash.instBackend.model.Like;
import com.vikash.instBackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepo extends JpaRepository<Comment,Integer> {
    List<Comment> findByInstaPost(Post myPost);

}



