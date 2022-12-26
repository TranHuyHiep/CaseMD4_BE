package com.codegym.service.relationship;

import com.codegym.model.Relationship;
import com.codegym.repository.IRelationshipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipService implements IRelationshipService {
    @Autowired
    IRelationshipRepo relationshipRepo;

    @Override
    public List<Relationship> findAll() {
        return relationshipRepo.findAll();
    }

    @Override
    public boolean save(Relationship relationship) {
        return relationshipRepo.save(relationship) != null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Relationship findById(Long id) {
        return null;
    }

    /**
     *
     * @param userId
     * @param targetId
     * @return Relationship between record
     */
    @Override
    public Relationship findByUserAndTarget(Long userId, Long targetId) {
        return relationshipRepo.findRelationshipByUserAndUserTarget(userId, targetId);
    }

    @Override
    public List<Relationship> findAllByUserId(Long userId, Long relationTypeId) {
        return relationshipRepo.findAllByUserAndRelationType_IdLike(userId, relationTypeId);
    }

    @Override
    public void acceptFriendRequest(Long userId, Long targetId) {

    }
}
