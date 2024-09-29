package com.cursojava.curso.controllers;

import com.cursojava.curso.DAO.UserDao;
import com.cursojava.curso.models.User;
import com.cursojava.curso.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.strategy.StdInstantiatorStrategy;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

 
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;


    private boolean validateToken(String token){
       String userId = jwtUtil.getKey(token);
       return userId != null;
    }


    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token){

        if(!validateToken(token)){return null;};
        
        return userDao.getUsers();
    }

    @RequestMapping(value = "api/user", method = RequestMethod.POST)
    public  void createUser(@RequestBody User user){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        userDao.createUser(user);
    }

    @RequestMapping(value = "api/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        if(!validateToken(token)){return;}
        userDao.deleteUser(id);
    }

}
