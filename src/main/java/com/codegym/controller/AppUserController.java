package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.model.AppUserDTO;
import com.codegym.service.appUserService.AppUserDTOService;
import com.codegym.service.appUserService.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class AppUserController {
    private long time;
    @Autowired
    IAppUserService appUserService;

    @Autowired
    AppUserDTOService appUserDTOService;

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getById(@PathVariable Long id){
        AppUser appUser = appUserService.findById(id);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }
    @GetMapping("displayname/{displayName}")
    public ResponseEntity<List<AppUser>> findByDisplayName(@PathVariable String displayName){
        List<AppUser> appUserList = appUserService.findByDisplayNameContaining(displayName);
        return new ResponseEntity<>(appUserList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> save(@RequestBody AppUser appUser){
        String img = this.time+"-"+appUser.getImage();
        if (appUser.getImage() == null){
            img = appUserService.findById(appUser.getId()).getImage();
        }
        appUser.setImage(img);
        return new ResponseEntity<>(appUserService.save(appUser), HttpStatus.OK);
    }
    @PostMapping("/saveImg")
    public ResponseEntity<Boolean> saveImg(@RequestBody MultipartFile img){
        this.time = System.currentTimeMillis();
        appUserDTOService.saveImg(img, this.time);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
