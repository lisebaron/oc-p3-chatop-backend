package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.dto.UserDto;
import com.chatopbackend.chatopbackend.mapping.UserMapping;
import com.chatopbackend.chatopbackend.model.ERole;
import com.chatopbackend.chatopbackend.model.Role;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.dto.payload.request.LoginRequest;
import com.chatopbackend.chatopbackend.dto.payload.request.SignupRequest;
import com.chatopbackend.chatopbackend.dto.payload.response.JwtResponse;
import com.chatopbackend.chatopbackend.dto.payload.response.MessageResponse;
import com.chatopbackend.chatopbackend.repository.RoleRepository;
import com.chatopbackend.chatopbackend.repository.UserRepository;
import com.chatopbackend.chatopbackend.utils.JwtUtils;
import com.chatopbackend.chatopbackend.security.services.UserDetailsImpl;
import com.chatopbackend.chatopbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserMapping userMapping;

    public AuthController(UserRepository userRepository, UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapping userMapping) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapping = userMapping;
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(Principal principal) {
        User user = userService.getUserByEmail(principal.getName()).orElse(null);
        return user != null ? userMapping.mapUserToUserDto(user) : null;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getEmail(),
                signUpRequest.getName(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getEmail(), signUpRequest.getPassword()));
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
