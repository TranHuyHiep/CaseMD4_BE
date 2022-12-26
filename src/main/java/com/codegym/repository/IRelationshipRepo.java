package com.codegym.repository;

import com.codegym.model.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRelationshipRepo extends JpaRepository<Relationship, Long> {
    Relationship findRelationshipByUserAndUserTarget(Long userid, Long TargetId);

    List<Relationship> findAllByRelationTypeLike(Long relationTypeId);

    List<Relationship> findAllByUserAndRelationType_IdLike(Long UserId, Long relationTypeId);
}
