package ecommerce_java_springboot.config;

import ecommerce_java_springboot.models.UserModel;
import ecommerce_java_springboot.models.enums.Role;
import ecommerce_java_springboot.models.enums.UserStatus;
import ecommerce_java_springboot.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class CreateAdminRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createAdminUser() {
        return args -> {
            String adminEmail = "micaelalepore98@gmail.com";

            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                UserModel admin = UserModel.builder()
                        .name("Micaela")
                        .email(adminEmail)
                        .password(passwordEncoder.encode("Mica98"))
                        .role(Role.ADMIN)
                        .status(UserStatus.ACTIVE)
                        .build();

                userRepository.save(admin);

                System.out.println("Admin creado");
            } else {
                System.out.println("El admin ya existe");
            }
        };
    }
}
