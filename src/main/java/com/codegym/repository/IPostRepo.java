package com.codegym.repository;

import com.codegym.model.Account;
import com.codegym.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepo extends JpaRepository<Post, Long> {
    public List<Post> findAllByAppUserId(Long id);
}
