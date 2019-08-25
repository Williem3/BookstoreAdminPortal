package com.adminportal.service;


import com.adminportal.entity.User;
import com.adminportal.entity.security.UserRole;

import java.util.Set;

public interface UserService {


    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    User save(User user);
}
