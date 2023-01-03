package com.codegym.repository;

import com.codegym.model.Account;
import com.codegym.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepo extends JpaRepository<Post, Long> {
    public List<Post> findAllByAppUserId(Long id);

    @Query(value = "select * from post inner join app_user_friends on post.app_user_id = app_user_friends.app_user_id \n" +
            "where post.app_user_id = ?1 or friends_id = ?1 order by time", nativeQuery = true)
    public List<Post> findAllPostFriend(Long id);
}
