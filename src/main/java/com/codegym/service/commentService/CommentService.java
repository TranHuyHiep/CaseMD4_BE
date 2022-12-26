package com.codegym.service.commentService;

import com.codegym.model.Comment;
import com.codegym.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService{
    @Autowired
    private ICommentRepo iCommentRepo;
    @Override
    public List<Comment> findAll() {
        return iCommentRepo.findAll();
    }

    @Override
    public boolean save(Comment comment) {
        return iCommentRepo.save(comment) != null;
    }

    @Override
    public boolean delete(Long id) {
        if(iCommentRepo.existsById(id)){
            iCommentRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Comment findById(Long id) {
        if (iCommentRepo.findById(id).isPresent()){
            return iCommentRepo.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Comment> findCommentByPostId(Long id) {
        return iCommentRepo.findCommentByPostId(id);
//        return null;
    }
}
