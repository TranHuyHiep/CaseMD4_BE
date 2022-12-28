package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.model.Relationship;
import com.codegym.service.relationType.RelationTypeService;
import com.codegym.service.relationship.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/relationship")
public class RelationshipController {
    private final Long FRIEND = 1l;
    private final Long PENDING = 2l;
    private final Long FOLLOW = 3l;
    private final Long BLOCK = 4l;
    private final Long NO = 5l;
    @Autowired
    RelationshipService relationshipService;
    @Autowired
    RelationTypeService relationTypeService;

    @PostMapping("/sentFriendRequest/{userId}/{targetId}")
    public ResponseEntity<Relationship> sentFriendRequest(@PathVariable Long userId,
                                                          @PathVariable Long targetId) {
        relationshipService.save(Relationship.builder()
                .user(userId)
                .userTarget(targetId)
                .relationType(relationTypeService.findById(PENDING))
                .build());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cancelFriendRequest/{userId}/{targetId}")
    public ResponseEntity<Relationship> cancelFriendRequest(@PathVariable Long userId,
                                                            @PathVariable Long targetId) {
        relationshipService.delete(relationshipService.findByUserAndTarget(userId, targetId).getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/acceptFriendRequest/{userId}/{targetId}")
    public ResponseEntity<Relationship> acceptFriendRequest(@PathVariable Long userId,
                                                            @PathVariable Long targetId) {
        relationshipService.acceptFriendRequest(userId, targetId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}/{targetId}")
    public ResponseEntity<Relationship> getRelationById(@PathVariable Long userId,
                                                        @PathVariable Long targetId) {
        Relationship relationship = relationshipService.findByUserAndTarget(userId, targetId);
        return new ResponseEntity<>(relationship, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Relationship>> getAllFriendRequests(@PathVariable Long userId,
                                                                   @RequestParam("relation")  Long relation) {
        List<Relationship> relationships = relationshipService.findAllByUserId(userId, relation);
        return new ResponseEntity<>(relationships, HttpStatus.OK);
    }


}
