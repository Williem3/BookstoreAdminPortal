package com.adminportal.service.serviceimpl;


import com.adminportal.dao.RoleDAO;
import com.adminportal.dao.UserDAO;
import com.adminportal.entity.User;
import com.adminportal.entity.security.UserRole;
import com.adminportal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception{
        User localUser = userDAO.findByUsername(user.getUsername());

        if (localUser != null) {
            LOG.info("user already exists. Nothing will be done");
        } else {
            for (UserRole ur : userRoles) {
                roleDAO.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            localUser = userDAO.save(user);
        }
        return localUser;
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }
}
