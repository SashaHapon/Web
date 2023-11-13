package org.food.security.security.service.api;

import org.food.security.model.User;
import org.food.security.payload.request.LoginRequest;
import org.food.security.payload.request.SignupRequest;
import org.food.security.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    ResponseEntity authenticateUser(LoginRequest loginRequest);
    MessageResponse registerUser(SignupRequest signupRequest);

    ResponseEntity logoutUser();

    ResponseEntity refreshToken(HttpServletRequest request);

    MessageResponse addModeratorRole(User user);

    MessageResponse addAdminRole(User user);
}
