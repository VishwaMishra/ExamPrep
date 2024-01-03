package com.exam.examservice.service;

import com.exam.examservice.entity.User;
import com.exam.examservice.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {

    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    public User getUser(String username);

    public void deleteUser(Long userId);

}
