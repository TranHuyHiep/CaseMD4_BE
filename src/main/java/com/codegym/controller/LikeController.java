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

import java.util.Optional;

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
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(iLikeService.findAll(), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> findLikeByAccountAndPost(@RequestBody Likes like){
        Optional<Likes> flag = iLikeService.findByIdAndPost(like.getAppUser().getId(), like.getPost().getId());
        if(flag.isPresent()){
            deletdLike(flag.get().getId());
        }else {
              createLike(like);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public void createLike(Likes newLike){
        if(newLike.getPost() != null) {
            Post post = iPostService.findById(newLike.getPost().getId());
            post.setLikeCount(post.getLikeCount() + 1);
            iPostService.save(post);
        }
        else {
            Comment comment = iCommentService.findById(newLike.getCmt().getId());
            comment.setLikeCount(comment.getLikeCount() + 1);
            iCommentService.save(comment);
        }
        iLikeService.save(newLike);
    }

    public void deletdLike(Long id){
        Likes like = iLikeService.findById(id);
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
      iLikeService.delete(id);
    }
}
