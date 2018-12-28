package org.mak.controller;

import org.mak.model.RegisterDTO;
import org.mak.service.MakService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MakController {

    @Autowired
    MakService makService;

    Logger logger = LoggerFactory.getLogger(MakController.class);

    @RequestMapping("/ping")
    public String alive() {
        return "MakUserAPI is healthy!";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO)
    {
        makService.signUp(registerDTO);
        return ResponseEntity.ok("Success");

    }
}
