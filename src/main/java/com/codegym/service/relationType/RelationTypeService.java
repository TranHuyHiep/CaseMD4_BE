package com.codegym.service.relationType;

import com.codegym.model.RelationType;
import com.codegym.repository.IRelationTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationTypeService implements IRelationTypeService{
    @Autowired
    private IRelationTypeRepo relationTypeRepo;
    @Override
    public List<RelationType> findAll() {
        return relationTypeRepo.findAll();
    }

    @Override
    public boolean save(RelationType relationType) {
        return relationTypeRepo.save(relationType) != null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public RelationType findById(Long id) {
        return relationTypeRepo.findById(id).orElse(null);
    }


}
