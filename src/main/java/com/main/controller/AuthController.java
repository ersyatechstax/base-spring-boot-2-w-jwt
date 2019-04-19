package com.main.controller;

import com.main.config.security.JwtTokenProvider;
import com.main.persistence.repository.RoleRepository;
import com.main.persistence.repository.UserRepository;
import com.main.services.AuthService;
import com.main.vo.LoginVO;
import com.main.vo.RegisterVO;
import com.main.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * created by ersya 30/03/2019
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseVO> doLogin(@RequestBody LoginVO loginVO, Errors errors){
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return authService.doLogin(loginVO);
            }
        };
        return handler.getResultWithValidation(errors);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseVO> registerUser(@Valid @RequestBody RegisterVO registerVO, Errors errors){
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return authService.register(registerVO);
            }
        };
        return handler.getResultWithValidation(errors);
    }
}
