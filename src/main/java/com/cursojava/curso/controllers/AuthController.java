package com.cursojava.curso.controllers;


import com.cursojava.curso.DAO.UserDao;
import com.cursojava.curso.models.User;
import com.cursojava.curso.utils.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user){

        User loggedInUser = userDao.getUserByCredentials(user);
        
        if(loggedInUser != null){

            String tokenJwt = jwtUtil.create(String.valueOf(loggedInUser.getId()), loggedInUser.getEmail());
            return tokenJwt;
        }
       return "Fail";
    }
}
