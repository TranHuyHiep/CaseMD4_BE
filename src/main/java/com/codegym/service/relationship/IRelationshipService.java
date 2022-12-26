package com.codegym.service.relationship;

import com.codegym.model.Relationship;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IRelationshipService extends IGeneralService<Relationship> {
    Relationship findByUserAndTarget(Long userId, Long targetId);
    List<Relationship> findAllByUserId(Long userId, Long relationTypeId);
    void acceptFriendRequest(Long userId, Long targetId);
}
