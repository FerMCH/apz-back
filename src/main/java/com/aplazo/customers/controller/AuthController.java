package com.aplazo.customers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplazo.customers.model.request.LoginRequest;
import com.aplazo.customers.model.request.UserRequest;
import com.aplazo.customers.service.AtuhService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("${routes.auth.base}")
public class AuthController {

    private AtuhService atuhService;

    @PostMapping("${routes.auth.register}")
    public ResponseEntity<Object> registerUser(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(this.atuhService.createUser(
            userRequest.getUsername(), userRequest.getPassword()), HttpStatus.CREATED);
    }

    @PostMapping("${routes.auth.login}")
    public ResponseEntity<Object> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok("jwt");
    }

}
