package org.food.security.controller;

import org.food.security.model.User;
import org.food.security.security.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping("/mod")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addModeratorRole(User user){
        return userService.addModeratorRole(user);
    }

    @ResponseBody
    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addAdminRole(User user){
        return userService.addAdminRole(user);
    }

}
