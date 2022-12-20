package ru.kata.spring.boot_security.demo.initDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class InitDb {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InitDb(UserService userService, RoleService roleService) {
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

            User admin1 = new User("admin1", "admin1"
                    , "admin1", "admin1", 10
                    , "admin1@mail.ru", new HashSet<>(Arrays.asList(userRole, adminRole)));
            userService.save(admin1);

            User admin2 = new User("admin2", "admin2"
                    , "admin2", "admin2", 20
                    , "admin2@mail.ru", new HashSet<>(Arrays.asList(adminRole)));
            userService.save(admin2);

            User user1 = new User("user1", "user1"
                    , "user1", "user1", 34
                    ,"user1@mail.ru", new HashSet<>(Arrays.asList(userRole)));
            userService.save(user1);

            User user2 = new User("user2", "user2"
                    , "user2", "user2", 35
                    , "user2@mail.ru", new HashSet<>(Arrays.asList(userRole)));
            userService.save(user2);

            User user3 = new User("qw", "qw"
                    , "qwN", "qwS", 45
                    , "qw@qw", new HashSet<>(Arrays.asList(adminRole)));
            userService.save(user3);

        }
    }
}
