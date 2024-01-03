package com.exam.examservice.service.impl;

import com.exam.examservice.entity.User;
import com.exam.examservice.entity.UserRole;
import com.exam.examservice.repo.RoleRepository;
import com.exam.examservice.repo.UserRepository;
import com.exam.examservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //Create User
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
       User userInDB= this.userRepository.findByUsername(user.getUsername());

       if(userInDB!=null){
           System.out.println("User is present in DB");
           throw new Exception("User is already Exist !!");
       }
        else{
            for(UserRole ur:userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            userInDB=this.userRepository.save(user);

       }
        return userInDB;
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
