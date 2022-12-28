package com.codegym.repository;

import com.codegym.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ILikeRepo extends JpaRepository<Likes, Long> {
    @Query("select e from Likes e where e.appUser.id = ?1 and  e.post.id = ?2")
    Optional<Likes> findByAppUserAndPost(Long id, Long post);
}
