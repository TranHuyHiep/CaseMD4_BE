package com.codegym.service.postService;

import com.codegym.model.Post;
import com.codegym.repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService{
    @Autowired
    private IPostRepo iPostRepo;

    @Override
    public List<Post> findAll() {
        List<Post> list = iPostRepo.findAll();
        return list;
    }

    @Override
    public boolean save(Post post) {
         boolean check = iPostRepo.existsById(post.getId());
         if(!check) {// check cai nay lam gi?
             iPostRepo.save(post);
             return true;
         }
         return false;
    }

    @Override
    public boolean delete(Long id) {
        if(iPostRepo.existsById(id)) {
            iPostRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Post findById(Long id) {
        return iPostRepo.findById(id).get();
    }

    public List<Post> findAllByAppUserId(Long id){
        return iPostRepo.findAllByAppUserId(id);
    }
    public List<Post> findAllPostFriend(Long id){
        return iPostRepo.findAllPostFriend(id);
    }
}
