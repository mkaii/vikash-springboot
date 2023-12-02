package com.vikas.instaBackend.controller;


import com.vikas.instaBackend.model.Post;
import com.vikas.instaBackend.model.User;
import com.vikas.instaBackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class UserController {

    @Autowired
    UserService userService;

    //user sign up
    @PostMapping("user/signup")
    public String userSignUp(@Valid @RequestBody User newUser)
    {

        return userService.userSignUp(newUser);
    }

    //user sign in

    @PostMapping("user/signIn/{email}/{password}")
    public String userSignIn(@PathVariable String email, @PathVariable String password)
    {
        return userService.userSignIn(email,password);
    }



    //user sign out

    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestParam String email, @RequestParam String token)
    {
        return userService.userSignOut(email,token);
    }

    //create post
    @PostMapping("InstaPost")
    public String createInstaPost(@RequestParam String email,@RequestParam String tokenValue, @RequestBody Post instaPost)
    {
        return userService.createInstaPost(email,tokenValue,instaPost);
    }


    //delete post
    @DeleteMapping("InstaPost/{postId}")
    public String deleteInstaPost(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId)
    {
        return userService.deleteInstaPost(email,tokenValue,postId);
    }


    //get all the likes for a particular post:
    @GetMapping("count/likes/post/{postId}")
    public String getLikesByPostId(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId)
    {
        return userService.getLikesByPostId(email,tokenValue,postId);
    }

    // add like -- liking some particular post

    @PostMapping("like/post/{postId}")
    public String addLike(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId)
    {
        return userService.addLike(email,tokenValue,postId);
    }

    // remove like - un-like some particular post
    @DeleteMapping("unlike/post/{postId}")
    public String removeLike(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId)
    {
        return userService.removeLike(email,tokenValue,postId);
    }

    //comment apis

    //get comments for a particular post


    @PostMapping("comment/post/{postId}")
    public String addComment(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId,@RequestBody String commentBody )
    {
        return userService.addComment(email,tokenValue,commentBody,postId);
    }


    @DeleteMapping("post/comment/{commentId}")
    public String removeComment(@RequestParam String email, @RequestParam String tokenValue,
                                @PathVariable Integer commentId)
    {
        return userService.removeComment(email,tokenValue,commentId);
    }


    @PostMapping("follow/user/{targetUserId}")
    public String followTarget(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer targetUserId)
    {
        return userService.followTarget(email,tokenValue,targetUserId);
    }


}
