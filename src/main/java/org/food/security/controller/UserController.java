package org.food.security.controller;

import lombok.RequiredArgsConstructor;
import org.food.security.model.User;
import org.food.security.payload.response.MessageResponse;
import org.food.security.security.service.api.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/mod")
    @PreAuthorize("hasRole(ROLE_ADMIN)")
    public MessageResponse addModeratorRole(User user){
        return userService.addModeratorRole(user);
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole(ROLE_ADMIN)")
    public MessageResponse addAdminRole(User user){
        return userService.addAdminRole(user);
    }

}
