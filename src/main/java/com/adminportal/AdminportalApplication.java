package com.adminportal;

import com.adminportal.entity.User;
import com.adminportal.entity.security.Role;
import com.adminportal.entity.security.UserRole;
import com.adminportal.service.UserService;
import com.adminportal.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AdminportalApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(AdminportalApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        User user1 = new User();

        user1.setUsername("admin");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("admiin"));
        user1.setEmail("admin@gmail.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role = new Role();

        role.setRoleId(0);
        role.setName("ROLE_ADMIN");
        userRoles.add(new UserRole(user1, role));

        userService.createUser(user1, userRoles);
    }
}
