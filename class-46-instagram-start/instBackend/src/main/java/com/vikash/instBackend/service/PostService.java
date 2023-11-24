package com.vikash.instBackend.service;

import com.vikash.instBackend.model.Post;
import com.vikash.instBackend.repo.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {
    @Autowired
    IPostRepo postRepo;

    @Autowired
    LikeService likeService;

    @Autowired
    CommentService commentService;


    public void createInstaPost(Post instaPost) {
        instaPost.setPostCreatedTimeStamp(LocalDateTime.now());
        postRepo.save(instaPost);
    }

    public Post getPostById(Integer postId) {
        return postRepo.findById(postId).orElseThrow();
    }

    public void removeById(Integer postId) {
        Post myPost = postRepo.findById(postId).orElseThrow();

        // remove all likes for this post '
        likeService.clearLikesPost(myPost);

        // remove all comments for this post
        commentService.clearCommentsByPost(myPost);

        // finally remove all the post after likes and comment are removed for that post
        postRepo.delete(myPost);





    }

   public String getLikesForPost(Integer postId) {

      Post instaPost = postRepo.findById(postId).orElseThrow();

       return likeService.getLikesByPost(instaPost);
    }


}
