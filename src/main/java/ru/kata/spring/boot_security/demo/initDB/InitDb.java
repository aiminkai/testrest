package ru.kata.spring.boot_security.demo.initDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitDb {
    private final UserServiceImpl userService;
    private final RoleService roleService;

    @Autowired
    public InitDb(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void postConstruct() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            Role adminRole = new Role("ROLE_ADMIN");
            Role userRole = new Role("ROLE_USER");
            roleService.addRole(adminRole);
            roleService.addRole(userRole);

            List<Role> testRoles = new ArrayList<>();
            testRoles.add(adminRole);
            testRoles.add(userRole);
            List<Role> adminRoles = new ArrayList<>();
            adminRoles.add(adminRole);
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(userRole);


            userService.createUser(new User("test@mail.com", "test", "test", "test",  24, testRoles));
            userService.createUser(new User("admin@mail.com", "admin", "admin", "admin", 45, adminRoles));
            userService.createUser(new User("user@mail.com", "user", "user", "user", 35, userRoles));
            userService.createUser(new User("q", "q", "q", "q", 35,testRoles ));
            userService.createUser(new User("a", "a", "a", "a", 55,userRoles ));

        }
    }
}
