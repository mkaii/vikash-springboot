package com.vikas.instaBackend.service;


import com.vikas.instaBackend.model.Comment;
import com.vikas.instaBackend.model.Post;
import com.vikas.instaBackend.repo.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {


    @Autowired
    ICommentRepo commentRepo;

    public void clearCommentsByPost(Post myPost) {

        //find all the comments for the myPost

        List<Comment> postComments = commentRepo.findByInstaPost(myPost);

        commentRepo.deleteAll(postComments);

    }
}
