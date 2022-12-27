package com.codegym.service.likeService;

import com.codegym.model.Likes;
import com.codegym.repository.ILikeRepo;
import com.codegym.service.IGeneralService;

public interface ILikeService extends IGeneralService<Likes> {
    public boolean findByIdAndPost(Long id, Long post);
}
