package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository
            , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Transactional
    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUsername(user.getUsername());
        userRepository.save(user);
    }

    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUser(long id) {
        return userRepository.getById(id);
    }

    @Transactional
    @Override
    public void editUser(long id, User user) {
        User userToBeEdited = userRepository.getById(id);
        userToBeEdited.setName(user.getName());
        userToBeEdited.setUsername(user.getUsername());
        userToBeEdited.setPassword(passwordEncoder.encode(user.getPassword()));
        userToBeEdited.setRoles(user.getRoles());
        userToBeEdited.setAge(user.getAge());
        userToBeEdited.setSurname(user.getSurname());
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
