package com.vikas.instaBackend.service;


import com.vikas.instaBackend.model.AuthenticationToken;
import com.vikas.instaBackend.model.Post;
import com.vikas.instaBackend.model.User;
import com.vikas.instaBackend.repo.IUserRepo;
import com.vikas.instaBackend.service.EmailUtility.MailHandlerBase;
import com.vikas.instaBackend.service.HashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    PostService postService;


    public String userSignUp(User newUser) {

        //check if already exist -> Not allowed : try logging in

        String newEmail = newUser.getUserEmail();

        User existingUser = userRepo.findByUserEmail(newEmail);

        if(existingUser != null)
        {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = newUser.getUserPassword();

        //hashing logic to convert the password into some hashed password to be stored in the table

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            newUser.setUserPassword(encryptedPassword);
            newUser.setBlueTick(false);


            userRepo.save(newUser);
            return "Insta user registered!!!";


        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }

    public String userSignIn(String email, String password) {

        //check if the email is there in your tables-- this should be the case !!

        User existingUser = userRepo.findByUserEmail(email); //db call

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                //login should be allowed using token
                AuthenticationToken token  = new AuthenticationToken(existingUser);

                if(MailHandlerBase.sendEmail(email,"otp after login", token.getTokenValue())) {
                    authenticationService.createToken(token);
                    return "check email for otp/token!!!";
                }
                else {
                    return "error while generating token!!!";
                }


            }
            else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }

    public String userSignOut(String email, String tokenValue) {

        if(authenticationService.authenticate(email,tokenValue)) {

            //delete the token row
            authenticationService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else
        {
            return "Un Authenticated access!!!";
        }

    }

    public String createInstaPost(String email, String tokenValue, Post instaPost) {

        if(authenticationService.authenticate(email,tokenValue)) {

           // insta post for this particular logged in user :

            User existingUser = userRepo.findByUserEmail(email);
            instaPost.setPostOwner(existingUser);

            //save the instagram post
            postService.createInstaPost(instaPost);
            return instaPost.getPostType() + " posted!!";

        }
        else
        {
            return "Un Authenticated access!!!";
        }

    }

    public String deleteInstaPost(String email, String tokenValue, Integer postId) {

        if(authenticationService.authenticate(email,tokenValue)) {

            Post instaPost =  postService.getPostById(postId);

            //
            if(instaPost.getPostOwner().getUserEmail().equals(email))
            {
                //finally delete the insta post
                postService.removeById(postId);
                return "post removed!!";
            }
            else {
                return "Un authorized access!!";
            }

        }
        else
        {
            return "Un Authenticated access!!!";
        }

    }

    public String getLikesByPostId(String email, String tokenValue, Integer postId) {

       return postService.getLikesForPost(postId);

    }
}
