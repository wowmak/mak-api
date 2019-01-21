package org.mak.controller;

import org.mak.error.DuplicateUsernameException;
import org.mak.model.UserDTO;
import org.mak.service.MakService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) throws DuplicateUsernameException {
        makService.signUp(userDTO);
        return ResponseEntity.ok("Success");

    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<String> test()
    {
        logger.info("Enter::Get User");
        String username="MakUser";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }

        logger.info("Exit::Get User::"+username);

        return ResponseEntity.ok("Welcome "+username);

    }
}
