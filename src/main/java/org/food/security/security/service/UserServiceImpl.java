package org.food.security.security.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.food.exception.classes.BadRequestException;
import org.food.exception.classes.TokenRefreshException;
import org.food.security.model.ERole;
import org.food.security.model.RefreshToken;
import org.food.security.model.Role;
import org.food.security.model.User;
import org.food.security.payload.request.LoginRequest;
import org.food.security.payload.request.SignupRequest;
import org.food.security.payload.response.MessageResponse;
import org.food.security.payload.response.UserInfoResponse;
import org.food.security.repository.RoleRepository;
import org.food.security.repository.UserRepository;
import org.food.security.security.jwt.JwtUtils;
import org.food.security.security.service.api.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @NonNull
    private AuthenticationManager authenticationManager;

    @NonNull
    private UserRepository userRepository;

    @NonNull
    private RoleRepository roleRepository;

    @NonNull
    private PasswordEncoder encoder;

    @NonNull
    private JwtUtils jwtUtils;

    @NonNull
    private RefreshTokenService refreshTokenService;

    @Override
    public ResponseEntity authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority()).toList();

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @Override
    public MessageResponse registerUser(SignupRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new BadRequestException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Error: Email is already in use!");
        }

        User user = createUser(signUpRequest);
        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }

    @Override
    public ResponseEntity logoutUser() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principle.toString() != "anonymousUser") {
            Long userId = ((UserDetailsImpl) principle).getId();
            refreshTokenService.deleteByUserId(userId);
        }

        ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
        ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new MessageResponse("You've been signed out!"));

    }

    @Override
    public ResponseEntity refreshToken(HttpServletRequest request) {
        String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);

        if ((refreshToken != null) && (refreshToken.length() > 0)) {
            return refreshTokenService.findByToken(refreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);

                        return ResponseEntity.ok()
                                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                                .body(new MessageResponse("Token is refreshed successfully!"));
                    })
                    .orElseThrow(() -> new TokenRefreshException(refreshToken,
                            "Refresh token is not in database!"));
        } else {
            throw new BadRequestException("Refresh Token is empty!");
        }
    }

    @Override
    public MessageResponse addModeratorRole(User user) {

        Role modRole = roleRepository.findByName(ERole.MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        userRepository.findByUsername(user.getUsername());
        user.getRoles().add(modRole);
        userRepository.save(user);
        return new MessageResponse("Moderator role added to:" + user.getUsername());
    }

    @Override
    public MessageResponse addAdminRole(User user) {

        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        userRepository.findByUsername(user.getUsername());
        user.getRoles().add(adminRole);
        userRepository.save(user);
        return new MessageResponse("Admin role added to:" + user.getUsername());
    }

    private User createUser(SignupRequest signUpRequest) {

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        return user;
    }
}
