package com.vikash.instBackend.service;

import com.vikash.instBackend.model.Comment;
import com.vikash.instBackend.model.Like;
import com.vikash.instBackend.model.Post;
import com.vikash.instBackend.repo.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    ICommentRepo commentRepo;

    public void clearCommentsByPost(Post myPost) {
        List<Comment> postComments = commentRepo.findByInstaPost(myPost);
        commentRepo.deleteAll(postComments);
    }
}
