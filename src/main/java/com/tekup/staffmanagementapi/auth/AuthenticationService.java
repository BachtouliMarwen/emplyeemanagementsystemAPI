package com.tekup.staffmanagementapi.auth;

import com.tekup.staffmanagementapi.config.JwtService;
import com.tekup.staffmanagementapi.user.Role;
import com.tekup.staffmanagementapi.user.User;
import com.tekup.staffmanagementapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (request.getFirstName() == null || request.getLastName() == null) {
            throw new IllegalArgumentException("First name and last name are required.");
        }
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.HR)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        String dashboardUrl;
        if (user.getRole() == Role.HR) {
            dashboardUrl ="/hr/employees";
        } else if (user.getRole() == Role.EMPLOYEE) {
            dashboardUrl ="/employee/dashboard";
        } else {
            dashboardUrl = "/";
        }
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .dashboardUrl(dashboardUrl)
                .role(user.getRole().name())
                .build();
    }
}
