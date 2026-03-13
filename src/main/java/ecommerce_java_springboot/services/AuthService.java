package ecommerce_java_springboot.services;

import ecommerce_java_springboot.common.exception.EmailAlreadyExistsException;
import ecommerce_java_springboot.common.exception.InvalidCredentialsException;
import ecommerce_java_springboot.common.exception.ResourceNotFoundException;
import ecommerce_java_springboot.dto.request.LoginRequest;
import ecommerce_java_springboot.dto.request.RegisterRequest;
import ecommerce_java_springboot.models.UserModel;
import ecommerce_java_springboot.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserModel login(LoginRequest request) {
        UserModel user = userRepository.findByEmail(request.email()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return user;
    }

    public UserModel register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        UserModel user = UserModel.builder().email(request.email()).name(request.name()).password(passwordEncoder.encode(request.password())).build();

        userRepository.save(user);

        return user;
    }
}
