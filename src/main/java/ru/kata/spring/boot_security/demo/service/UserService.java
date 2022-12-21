package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> getAllUsers();
    void deleteUser(long id);
    void editUser(long id, User user);
    User getUser(long id);
    User findByUsername(String username);

}
