package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    List<User> getAllUsers();
    void deleteUser(long id);
    void updateUser(User user);
    User getUserById(long id);
    User getUserByUsername(String username);

}
