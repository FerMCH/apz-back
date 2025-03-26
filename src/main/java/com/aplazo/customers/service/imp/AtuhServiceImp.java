package com.aplazo.customers.service.imp;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aplazo.customers.constants.Constants;
import com.aplazo.customers.model.entity.User;
import com.aplazo.customers.model.response.TokenResponse;
import com.aplazo.customers.service.AtuhService;
import com.aplazo.customers.service.UserService;
import com.aplazo.customers.utils.JwtUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AtuhServiceImp implements AtuhService{

    private final UserService userServiceImp;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Override
    public TokenResponse createUser(String username, String password) {
            User user = this.userServiceImp.createUser(username, passwordEncoder.encode(password));

            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(jwtUtils.buildToken(user, Constants.EXPIRATIO_TIME));
            return tokenResponse;
    }

    @Override
    public TokenResponse login(String username) {
        TokenResponse tokenResponse = new TokenResponse();
        this.userServiceImp.findByUsername(username);
        return tokenResponse;
    }

}
