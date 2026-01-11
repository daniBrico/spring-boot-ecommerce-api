package ecommerce_java_springboot.services;

import ecommerce_java_springboot.models.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${spring.security.jwt.secret-key}")
    private String secretKey;
    @Value("${spring.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${spring.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public String generateToken(final UserModel user) {
        return buildToken(user, jwtExpiration);
    }

    public String generateRefreshToken(final UserModel user) {
        return buildToken(user, refreshExpiration);
    }

    public String buildToken(final UserModel user, final long expiration) {
        return Jwts.builder()
                .id(user.getId().toString())
                .claims(Map.of("name", user.getName()))
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    // Genera una clave secreta utilizando el algoritmo hmacSha
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
