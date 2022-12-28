package com.codegym.service.likeService;

import com.codegym.model.Likes;
import com.codegym.repository.ILikeRepo;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface ILikeService extends IGeneralService<Likes> {
    public Optional<Likes> findByIdAndPost(Long id, Long post);
}
