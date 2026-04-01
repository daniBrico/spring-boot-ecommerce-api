package ecommerce_java_springboot.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class JwtCookieManager {

    public void addJwtCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("jwt", token);
        this.applyDefaultCookieSettings(cookie);
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);
    }

    public void clearJwtCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        this.applyDefaultCookieSettings(cookie);
        cookie.setMaxAge(0);

        response.addCookie(cookie);
    }

    public void applyDefaultCookieSettings(Cookie cookie) {
//        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
    }
}
