package com.codegym.controller;

import com.codegym.model.Comment;
import com.codegym.model.Post;
import com.codegym.service.commentService.ICommentService;
import com.codegym.service.postService.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private ICommentService iCommentService;
    @Autowired
    private IPostService iPostService;

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComment(){
        List<Comment> list = iCommentService.findAll();
        System.out.println(list);
            return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<Comment>> getCommentByPostId(@PathVariable Long id){
        List<Comment> list = iCommentService.findCommentByPostId(id);
        System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCommentById(@PathVariable Long id, @RequestBody Comment newComment){
        Comment comment = iCommentService.findById(id);
        if(comment == null){
            return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);
        }
        newComment.setId(comment.getId());
        return new ResponseEntity<>(iCommentService.save(newComment), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable Long id){
        Comment comment = iCommentService.findById(id);
        if(comment == null){
            return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);
        }
        Post post = iPostService.findById(comment.getPost().getId());
        post.setCmtCount(post.getCmtCount()-1);
        iPostService.save(post);
        return new ResponseEntity<>(iCommentService.delete(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody Comment newComment){
        Post post = iPostService.findById(newComment.getPost().getId());
        post.setCmtCount(post.getCmtCount()+1);
        iPostService.save(post);
        return new ResponseEntity<>(iCommentService.save(newComment), HttpStatus.CREATED);
    }
}
