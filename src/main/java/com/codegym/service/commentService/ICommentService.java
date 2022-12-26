package com.codegym.service.commentService;

import com.codegym.model.Comment;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface ICommentService extends IGeneralService<Comment> {
    List<Comment> findCommentByPostId(Long id);
}
