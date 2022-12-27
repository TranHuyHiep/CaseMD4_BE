package com.codegym.repository;

import com.codegym.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeRepo extends JpaRepository<Likes, Long> {
    @Modifying
    @Query("select e from Likes e where e.appUser.id = ?1 and e.cmt = ?2")
    Likes findByIdAndPost(Long id, Long post);
}
