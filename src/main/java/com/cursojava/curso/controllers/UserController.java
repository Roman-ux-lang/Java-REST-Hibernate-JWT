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

    // example method request
    @RequestMapping(value = "api/test")
    public String test(){
        return "Test";
    }

    //example method request
    @GetMapping("api/list")
    public List<String> myList(){
        return List.of("banana","apple","strawberry");
    }

    /*@RequestMapping(value = "users")
    public  List<User> getUsers(){

        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setId(12454L);
        user1.setName("Leonardo");
        user1.setSurname("Reséndiz Trejo");
        user1.setEmail("leonardo@outlook.com");
        user1.setPhone("55-44-55-67-88");

        User user2 = new User();
        user2.setId(23454L);
        user2.setName("Silvestre");
        user2.setSurname("Reséndiz Sánchez");
        user2.setEmail("silvestre@outlook.com");
        user2.setPhone("55--66-77-44-33-77");

        User user3 = new User();
        user3.setId(43235L);
        user3.setName("Macrina");
        user3.setSurname("Trejo Pérez");
        user3.setEmail("macrina@outlook.com");
        user3.setPhone("55-67-24-65-23");
        
        User user4 = new User();
        user4.setId(1234L);
        user4.setName("Román");
        user4.setSurname("Reséndiz Trejo");
        user4.setEmail("romanresendiztrejo@outlook.com");
        user4.setPhone("55-60-88-54-66");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        return  users;

    }*/

    @RequestMapping(value = "api/user/{id}")
    public User getUser(@PathVariable Long id){
        User user = new User();
        user.setId(id);
        user.setName("Román");
        user.setSurname("Reséndiz Trejo");
        user.setEmail("romanresneiztrejo@outlook.com");
        user.setPhone("55-60-90-88-75");
        return  user;

    }
}