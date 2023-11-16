package com.vikas.instaBackend.service;


import com.vikas.instaBackend.model.Like;
import com.vikas.instaBackend.model.Post;
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
}
