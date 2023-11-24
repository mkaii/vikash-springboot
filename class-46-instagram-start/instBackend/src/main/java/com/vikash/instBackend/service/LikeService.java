package com.vikash.instBackend.service;


import com.vikash.instBackend.model.Like;
import com.vikash.instBackend.model.Post;
import com.vikash.instBackend.repo.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    ILikeRepo likeRepo;

    public void clearLikesPost(Post myPost) {
        // find all the likes for the myPost
        List<Like> postLikes = likeRepo.findByInstaPost(myPost);
        likeRepo.deleteAll(postLikes);
    }


    public String getLikesByPost(Post instaPost) {

      return String.valueOf(likeRepo.findByInstaPost(instaPost).size());

   }
}
