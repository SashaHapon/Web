package org.food.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.food.security.payload.request.LoginRequest;
import org.food.security.payload.request.SignupRequest;
import org.food.security.security.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;

    @Autowired
    private AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signin")
    @ResponseBody
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return userService.registerUser(signUpRequest);
     }

    @PostMapping("/signout")
    @ResponseBody
    public ResponseEntity<?> logoutUser() {
        return userService.logoutUser();
    }

    @PostMapping("/refreshtoken")
    @ResponseBody
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
        return  userService.refreshToken(request);
    }
}