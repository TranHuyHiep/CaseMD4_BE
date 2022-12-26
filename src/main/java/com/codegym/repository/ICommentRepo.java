package com.codegym.repository;

import com.codegym.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface ICommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByPostId(Long id);
}
