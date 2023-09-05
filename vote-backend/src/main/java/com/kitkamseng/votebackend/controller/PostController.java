package com.kitkamseng.votebackend.controller;

import com.kitkamseng.votebackend.model.Post;
import com.kitkamseng.votebackend.model.User;
import com.kitkamseng.votebackend.repository.PostRepository;
import com.kitkamseng.votebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("http://localhost:3000")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PostMapping("/post")
    public ResponseEntity<String> createPost(
            @RequestParam("image") MultipartFile image,
            @RequestParam("postTitle") String postTitle,
            @RequestParam("postDescription") String postDescription,
            @RequestParam("userId") Long userId)
    {
        try{
            String fileName = UUID.randomUUID().toString() + "-" + image.getOriginalFilename();

            Path uploadPath = Paths.get("C:\\Users\\Marte\\OneDrive\\Desktop\\vote-backend\\vote-backend\\src\\images\\" + fileName);

            image.transferTo(uploadPath.toFile());

            String imageUrl = fileName;

            Post post = new Post(postTitle, postDescription, imageUrl, userId);

            postRepository.save(post);

            return ResponseEntity.ok("Post successfully created!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating post");
        }
    }

    @GetMapping("/post/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId){
        List<Post> posts = postRepository.findByUserId(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


}
