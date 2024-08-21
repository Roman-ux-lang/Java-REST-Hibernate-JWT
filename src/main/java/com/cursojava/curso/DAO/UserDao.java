package com.cursojava.curso.DAO;

import com.cursojava.curso.models.User;

import java.util.List;

public interface UserDao {
    
    List<User> getUsers();

    void createUser(User user);

    void deleteUser(Long id);

    User getUserByCredentials(User user);


}

