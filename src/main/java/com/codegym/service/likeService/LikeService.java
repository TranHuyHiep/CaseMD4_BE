package com.codegym.service.likeService;

import com.codegym.model.Likes;
import com.codegym.repository.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService implements ILikeService{
    @Autowired
    private ILikeRepo iLikeRepo;
    @Override
    public List<Likes> findAll() {
        return iLikeRepo.findAll();
    }

    @Override
    public boolean save(Likes likes) {
        return iLikeRepo.save(likes) != null;
    }

    @Override
    public boolean delete(Long id) {
        if(iLikeRepo.existsById(id)){
            iLikeRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Likes findById(Long id) {
        return iLikeRepo.findById(id).get();
    }

    @Override
    public Optional<Likes> findByIdAndPost(Long id, Long post){
        Optional<Likes> k = iLikeRepo.findByAppUserAndPost(id, post);
       if(k.isPresent()){
           return k;
       }
       return Optional.empty();
    }
}
