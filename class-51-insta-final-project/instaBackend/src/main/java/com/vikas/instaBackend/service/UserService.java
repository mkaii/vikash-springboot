package com.vikas.instaBackend.service;


import com.vikas.instaBackend.model.*;
import com.vikas.instaBackend.repo.IUserRepo;
import com.vikas.instaBackend.service.EmailUtility.MailHandlerBase;
import com.vikas.instaBackend.service.HashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    PostService postService;

    @Autowired
    LikeService likeService;

    @Autowired
    CommentService commentService;

    @Autowired
    FollowService followService;


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

    public String addLike(String email, String tokenValue, Integer postId) {

        if(authenticationService.authenticate(email,tokenValue)) {

            //figure out the actual post
            Post instaPost =  postService.getPostById(postId);


            //figure out the user - corresponding to the email -> liker
            User liker = userRepo.findByUserEmail(email);



            // first check whether this liker has already liked this insta post

            boolean alreadyLiked = likeService.isAlreadyLiked(instaPost,liker);

            if(!alreadyLiked)
            {
                Like newLike = new Like(null,instaPost,liker);

                likeService.addLike(newLike);

                return liker.getUserHandle() + " liked " +  postId;
            }
            else {
                return "already liked";
            }


        }
        else
        {
            return "Un Authenticated access!!!";
        }

    }

    public String removeLike(String email, String tokenValue, Integer postId) {

        if(authenticationService.authenticate(email,tokenValue)) {

            //figure out the actual post
            Post instaPostToBeUnLiked =  postService.getPostById(postId);


            //figure out the user - corresponding to the email -> unliker
            User unliker = userRepo.findByUserEmail(email);


            return likeService.removeLikesByLikerAndPost(unliker,instaPostToBeUnLiked);


        }
        else
        {
            return "Un Authenticated access!!!";
        }


    }

    public String addComment(String email, String tokenValue, String commentBody, Integer postId) {

        if(authenticationService.authenticate(email,tokenValue)) {

            //figure out the post which we are commenting
            Post instaPostToBeCommented = postService.getPostById(postId);

            //we have to figure out the commentor
            User commentor = userRepo.findByUserEmail(email);

            // functionally more than 1 comment can be made on a particular post

            Comment newComment = new Comment(null,commentBody,
                    LocalDateTime.now(), instaPostToBeCommented, commentor);

            commentService.addComment(newComment);

            return commentor.getUserHandle() + " commented on " + postId;


        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String removeComment(String email, String tokenValue, Integer commentId) {

        if(authenticationService.authenticate(email,tokenValue)) {

            Comment comment = commentService.findCommentById(commentId);

            Post instaPostOfComment = comment.getInstaPost();

            if(authorizeCommentRemover(email,instaPostOfComment,comment))
            {
                commentService.removeCommentById(commentId);
                return "comment deleted";
            }
            else {
                return "Not authorized!!";
            }

        }
        else {
            return "Un Authenticated access!!!";
        }

    }

    private boolean authorizeCommentRemover(String email, Post instaPostOfComment, Comment comment) {

        User potentialRemover = userRepo.findByUserEmail(email);

        //only the commentor and the owner of the post should be allowed to remove a comment

        return potentialRemover.equals(instaPostOfComment.getPostOwner()) || potentialRemover.equals(comment.getCommenter());
    }

    public String followTarget(String email, String tokenValue, Integer targetUserId) {

        if(authenticationService.authenticate(email,tokenValue)) {

            User follower = userRepo.findByUserEmail(email);
            User target = userRepo.findById(targetUserId).orElseThrow();

            if(followService.authorizeToFollow(follower,target))
            {
                followService.startFollowing(follower,target);
                return follower.getUserHandle() + " started following " + target.getUserHandle();
            }
            else {
                return "Already follows, cannot re-follow";
            }

        }
        else {
            return "Un Authenticated access!!!";
        }
    }
}
