package com.vikash.instBackend.service;


import com.vikash.instBackend.model.AuthenticationToken;
import com.vikash.instBackend.model.Post;
import com.vikash.instBackend.model.User;
import com.vikash.instBackend.repo.IUserRepo;
import com.vikash.instBackend.service.HashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthService authService;

    @Autowired
    PostService postService;

    public String userSignUp(User newUser) {
        //check if user already exists
        String newEmail = newUser.getUserEmail();
        User ifExistUser = userRepo.findByUserEmail(newEmail);
        if (ifExistUser != null) {
            return "email already exists please enter unused one!";

        }
        String currentPassword = newUser.getUserPassword();
        try {
            String encryptedPass = PasswordEncryptor.encrypt(currentPassword);
            newUser.setUserPassword(encryptedPass);
            newUser.setBlueTick(false);
            userRepo.save(newUser);
            return "account created!";

        } catch (NoSuchAlgorithmException e) {
            return "internal server issue while saving password,try again!";

        }


    }


    public String UserSignIn(String email, String password) {

        // check if user exists via the email

        User existingUser = userRepo.findByUserEmail(email);
        if (existingUser == null) {
            return "not valid email,please sign up first!";

        }
        try {
            String encryptedPass = PasswordEncryptor.encrypt(password);
            if (existingUser.getUserPassword().equals(encryptedPass)) {
                // login should be allowed using token
                AuthenticationToken token = new AuthenticationToken(existingUser);
                authService.createToken(token);
                return "check email for otp/token ";


            } else {
                return "invalid username or password";
            }


        } catch (NoSuchAlgorithmException e) {
            return "internal server issue while saving password,try again!";


        }

    }

    public String userSgnOut(String email, String token) {


        if (authService.authenticate(email, token)) {
            authService.deleteToken(token);
            return "sign out successfull";

        } else {
            return "un authorized access";
        }


    }

    public String createInstaPost(String email, String tokenValue, Post instaPost) {
        if (authService.authenticate(email, tokenValue)) {

            // insta post for this particular logged in user
            User existingUser = userRepo.findByUserEmail(email);
            instaPost.setPostOwner(existingUser);

            //saving the post
            postService.createInstaPost(instaPost);
            return instaPost.getPostType() + "posted! ";
        } else {
            return "Un Authenticated access!!!";
        }
    }

    public String deleteInstaPost(String email, String tokenValue, Integer postId) {

        if (authService.authenticate(email, tokenValue)) {

            // insta post for this particular logged in user
            Post instaPost = postService.getPostById(postId);

            if (instaPost.getPostOwner().getUserEmail().equals(email)) {
                /// finally deletede insta post
                postService.removeById(postId);

                return "post removed";


            } else {
                return "Un authorized access!!";

            }


        }
        else{
            return "Un Authenticated access!!!";
        }
    }




    public String getLikesByPostId( Integer postId) {

       return postService.getLikesForPost(postId);



   }
}




