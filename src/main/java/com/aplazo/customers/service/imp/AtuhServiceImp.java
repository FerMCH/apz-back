package com.aplazo.customers.service.imp;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aplazo.customers.constants.Constants;
import com.aplazo.customers.constants.ErrorConstants;
import com.aplazo.customers.exception.error.BadRequestException;
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

    private final AuthenticationManager authenticationManager;

    @Override
    public TokenResponse createUser(String username, String password) {

        if(this.userServiceImp.findByUsername(username) != null) {
            throw new BadRequestException(ErrorConstants.INVALID_LOAN_REQUEST, ErrorConstants.ERROR_DETAIL);
        }
    
        User user = this.userServiceImp.createUser(username, passwordEncoder.encode(password));

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(jwtUtils.buildToken(user, Constants.EXPIRATIO_TIME));
        return tokenResponse;
    }

    @Override
    public TokenResponse login(String username, String password) {
        TokenResponse tokenResponse = new TokenResponse();
        this.authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password)  
        );
        User user = this.userServiceImp.findByUsername(username);
        tokenResponse.setToken(jwtUtils.buildToken(user, Constants.EXPIRATIO_TIME));

        

        return tokenResponse;
    }

}
