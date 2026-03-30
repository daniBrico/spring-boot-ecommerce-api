package ecommerce_java_springboot.services;

import ecommerce_java_springboot.common.exception.EmailAlreadyExistsException;
import ecommerce_java_springboot.common.exception.InvalidCredentialsException;
import ecommerce_java_springboot.dto.request.LoginRequest;
import ecommerce_java_springboot.dto.request.RegisterRequest;
import ecommerce_java_springboot.models.UserModel;
import ecommerce_java_springboot.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserModel login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        return (UserModel) authentication.getPrincipal();
    }

    public UserModel register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        UserModel user = UserModel.builder().email(request.email()).name(request.name()).password(passwordEncoder.encode(request.password())).build();

        userRepository.save(user);

        return user;
    }

    public UserModel getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return (UserModel) authentication.getPrincipal();
    }
}
