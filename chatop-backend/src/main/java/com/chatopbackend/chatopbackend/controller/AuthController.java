package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.dto.UserDto;
import com.chatopbackend.chatopbackend.dto.payload.request.LoginRequest;
import com.chatopbackend.chatopbackend.dto.payload.request.SignupRequest;
import com.chatopbackend.chatopbackend.dto.payload.response.JwtResponse;
import com.chatopbackend.chatopbackend.exception.EmailAlreadyInUseException;
import com.chatopbackend.chatopbackend.exception.RoleNotFoundException;
import com.chatopbackend.chatopbackend.exception.UserNotFoundException;
import com.chatopbackend.chatopbackend.mapping.UserMapping;
import com.chatopbackend.chatopbackend.model.ERole;
import com.chatopbackend.chatopbackend.model.Role;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.repository.RoleRepository;
import com.chatopbackend.chatopbackend.repository.UserRepository;
import com.chatopbackend.chatopbackend.service.UserService;
import com.chatopbackend.chatopbackend.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthenticationManager authenticationManager;
    JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserMapping userMapping;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserRepository userRepository, UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapping userMapping) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapping = userMapping;
    }

    /**
     * Retrieves the user based on the authenticated principal.
     *
     * @param principal the authenticated principal representing the user
     * @return the DTO representation of the user
     * @throws UserNotFoundException if the user is not found
     */
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(final Principal principal) {
        return userService.getUserByEmail(principal.getName())
                .map(userMapping::mapUserToUserDto).orElseThrow(()-> new UserNotFoundException("User not found"));
    }

    /**
     * Registers a new user based on the provided signup request.
     *
     * @param signUpRequest the signup request containing user details
     * @return ResponseEntity containing JWT response upon successful registration
     * @throws EmailAlreadyInUseException if the email provided is already registered
     */
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registerUser(final @Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailAlreadyInUseException("Email already exists.");
        }

        final User user = new User(signUpRequest.getEmail(),
                signUpRequest.getName(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        final Set<Role> roles = new HashSet<>();

        final Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RoleNotFoundException("Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getEmail(), signUpRequest.getPassword()));
        final String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    /**
     * Authenticates a user based on the provided login request.
     *
     * @param loginRequest the login request containing user credentials
     * @return ResponseEntity containing JWT response upon successful authentication
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(final @Valid @RequestBody LoginRequest loginRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
