package com.vikas.instaBackend.repo;

import com.vikas.instaBackend.model.Comment;
import com.vikas.instaBackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepo extends JpaRepository<Comment,Integer> {

    List<Comment> findByInstaPost(Post myPost);
}
