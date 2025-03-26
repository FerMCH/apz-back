package com.aplazo.customers.service.imp;

import org.springframework.stereotype.Service;

import com.aplazo.customers.model.entity.User;
import com.aplazo.customers.repository.UserRepository;
import com.aplazo.customers.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    @Override
    public User createUser(String username, String password) {
        User newUser = new User();
        newUser.setEnabled(true);
        newUser.setUsername(username);
        newUser.setPassword(password);

        return this.userRepository.save(newUser);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

}
