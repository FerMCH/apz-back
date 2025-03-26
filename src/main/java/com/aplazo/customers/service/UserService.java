package com.aplazo.customers.service;

import com.aplazo.customers.model.entity.User;

public interface UserService {

    User createUser(String username, String password);

    User findByUsername(String username);



}
