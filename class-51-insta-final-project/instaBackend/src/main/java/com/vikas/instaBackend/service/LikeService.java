package com.vikas.instaBackend.service;


import com.vikas.instaBackend.model.Like;
import com.vikas.instaBackend.model.Post;
import com.vikas.instaBackend.model.User;
import com.vikas.instaBackend.repo.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    ILikeRepo likeRepo;

    public void clearLikesByPost(Post myPost) {

        //find all the likes for the myPost

        List<Like> postLikes  = likeRepo.findByInstaPost(myPost);

        //remove the likes
        likeRepo.deleteAll(postLikes);


    }

    public String getLikesByPost(Post instaPost) {

       return String.valueOf(likeRepo.findByInstaPost(instaPost).size());

    }

    public void addLike(Like newLike) {
        likeRepo.save(newLike);
    }

    //check if insta- post and liker combination exist in the like table

    public boolean isAlreadyLiked(Post instaPost, User liker) {

        List<Like> likes = likeRepo.findByInstaPostAndLiker(instaPost,liker);

        return likes != null && likes.size() != 0;

    }

    public String removeLikesByLikerAndPost(User unliker, Post instaPostToBeUnLiked) {

        //fetch the like record created by this unliker on the instaPost
        List<Like> likes = likeRepo.findByInstaPostAndLiker(instaPostToBeUnLiked,unliker);

        likeRepo.deleteAll(likes);

        return "removed like";

    }
}
