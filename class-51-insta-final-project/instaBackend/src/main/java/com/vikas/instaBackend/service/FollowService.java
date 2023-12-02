package com.vikas.instaBackend.service;

import com.vikas.instaBackend.model.Follow;
import com.vikas.instaBackend.model.User;
import com.vikas.instaBackend.repo.IFollowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {

    @Autowired
    IFollowRepo followRepo;

    public boolean authorizeToFollow(User follower, User target) {

        //check if already follows or not

        Follow follow =  followRepo.findByCurrentUserAndCurrentUserFollower(target,follower);

        return follow == null && !follower.equals(target);
    }

    public void startFollowing(User follower, User target) {
        Follow follow = new Follow(null,target,follower);
        followRepo.save(follow);
    }
}
