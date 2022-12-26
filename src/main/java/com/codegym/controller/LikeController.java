package com.codegym.controller;

import com.codegym.model.Comment;
import com.codegym.model.Likes;
import com.codegym.model.Post;
import com.codegym.service.commentService.ICommentService;
import com.codegym.service.likeService.ILikeService;
import com.codegym.service.postService.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/likes")
public class  LikeController {
    @Autowired
    private ILikeService iLikeService;
    @Autowired
    private IPostService iPostService;
    @Autowired
    private ICommentService iCommentService;

    @PostMapping
    public ResponseEntity<?> createLike(@RequestBody Likes newLike){
        if(newLike.getPost() != null) {
            Post post = iPostService.findById(newLike.getPost().getId());
            if (post == null) {
                return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
            }
            post.setLikeCount(post.getLikeCount() + 1);
            iPostService.save(post);
        }
        else {
            Comment comment = iCommentService.findById(newLike.getCmt().getId());
            if (comment == null) {
                return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);
            }
            comment.setLikeCount(comment.getLikeCount() + 1);
            iCommentService.save(comment);
        }
        return new ResponseEntity<>(iLikeService.save(newLike), HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletdLike(@PathVariable Long id){
        Likes like = iLikeService.findById(id);
        if(like == null){
            return new ResponseEntity<>("Like not found", HttpStatus.NOT_FOUND);
        }
        if (like.getPost() != null) {
            Post post = iPostService.findById(like.getPost().getId());
            post.setLikeCount(post.getLikeCount() - 1);
            iPostService.save(post);
        }
        else {
            Comment comment = iCommentService.findById(like.getCmt().getId());
            comment.setLikeCount(comment.getLikeCount() - 1);
            iCommentService.save(comment);
        }
        return new ResponseEntity<>(iLikeService.delete(id), HttpStatus.OK);
    }
}
