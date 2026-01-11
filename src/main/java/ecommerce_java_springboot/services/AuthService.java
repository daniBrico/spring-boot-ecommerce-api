package ecommerce_java_springboot.services;

import ecommerce_java_springboot.dto.RegisterRequest;
import ecommerce_java_springboot.dto.TokenResponse;
import ecommerce_java_springboot.models.TokenModel;
import ecommerce_java_springboot.models.UserModel;
import ecommerce_java_springboot.repositories.TokenRepository;
import ecommerce_java_springboot.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public TokenResponse register(RegisterRequest request) {
        var user = UserModel.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return new TokenResponse(jwtToken, refreshToken);
    }

    private void saveUserToken(UserModel user, String jwtToken) {
        var token = TokenModel.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenModel.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(token);
    }
}
