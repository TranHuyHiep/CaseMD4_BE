package com.codegym.repository;

import com.codegym.model.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IRelationshipRepo extends JpaRepository<Relationship, Long> {
    Relationship findRelationshipByUserAndUserTarget(Long userid, Long TargetId);

    List<Relationship> findAllByRelationTypeLike(Long relationTypeId);

    List<Relationship> findAllByUserAndRelationType_IdLike(Long UserId, Long relationTypeId);

    @Transactional
    @Modifying
    @Query("UPDATE Relationship p SET p.relationType.id = 1 WHERE p.id =:id")
    void changeFriend(@Param("id") Long id);

}
