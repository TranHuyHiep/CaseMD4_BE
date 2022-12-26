package com.codegym.controller;

import com.codegym.model.Account;
import com.codegym.model.AccountPrinciple;
import com.codegym.model.Post;
import com.codegym.model.Role;
import com.codegym.service.postService.IPostService;
import com.codegym.service.postService.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    /* ---------------- GET ALL Post ------------------------ */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> list = postService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    /* ---------------- GET POST BY ID ------------------------ */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPostById(@PathVariable Long id) {
        Post post = postService.findById(id);
        if (post != null) {
            return new ResponseEntity<Object>(post, HttpStatus.OK);
        }
        return new ResponseEntity<Object>("Not Found Post", HttpStatus.NO_CONTENT);
    }
    /* ---------------- CREATE NEW POST ------------------------ */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        if (postService.save(post)) {
            return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Post Existed!", HttpStatus.BAD_REQUEST);
        }
    }
    /* ---------------- DELETE POST ------------------------ */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePostById(@PathVariable Long id) {
        postService.delete(id);
        return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
    }
}
