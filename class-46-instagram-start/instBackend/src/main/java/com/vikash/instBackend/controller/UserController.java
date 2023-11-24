package com.vikash.instBackend.controller;


import com.vikash.instBackend.model.Post;
import com.vikash.instBackend.model.User;
import com.vikash.instBackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController

public class UserController {

    @Autowired
    UserService userService;


    // user sign up
    @PostMapping("user/signup")
    public String userSignUp(@Valid @RequestBody User newUser){
        return userService.userSignUp(newUser);
    }

    // user sign in

    @PostMapping("user/signIn/{email}/{password}")
    public String UserSignIn(@PathVariable String email,@PathVariable String password ){
        return  userService.UserSignIn(email,password);
    }


    // user sign out

    @DeleteMapping("user/signOut")
    public String userSgnOut(@RequestParam String email,@RequestParam String token ){
        return userService.userSgnOut(email,token);
    }

    // create post
    @PostMapping("InstaPost")
    public String createPost(@RequestParam String email, @RequestParam String tokenValue, @RequestBody Post instaPost){
        return userService.createInstaPost(email,tokenValue,instaPost);
    }


    @DeleteMapping("instaPost/{postId}")
    public String deletePost(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId){
        return userService.deleteInstaPost(email,tokenValue,postId);
    }

     // get all the likes for a perticular post

   @GetMapping("count/likes/post/{postId}")
   public String getLikesByPostId( @PathVariable Integer postId)
    {
       return userService.getLikesByPostId(postId);
   }





}
