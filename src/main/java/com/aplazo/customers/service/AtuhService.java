package com.aplazo.customers.service;

import com.aplazo.customers.model.response.TokenResponse;

public interface AtuhService {

    TokenResponse createUser(String username, String password);

    TokenResponse login(String username, String password);
}
