package com.codegym.repository;

import com.codegym.model.RelationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRelationTypeRepo extends JpaRepository<RelationType, Long> {

}
