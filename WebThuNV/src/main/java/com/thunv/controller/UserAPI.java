package com.thunv.controller;

import com.thunv.controller.output.login.*;
import com.thunv.dto.CustomUserDetails;
import com.thunv.service.impl.UsersService;
import com.thunv.util.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class UserAPI {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UsersService userDetailService;

    public UserAPI(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UsersService userDetailService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userDetailService = userDetailService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        //Xác thực thông tin người dùng Request lên
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(jwt));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signupRequest){
        userDetailService.registerUser(signupRequest);
        return  ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
