package ecommerce_java_springboot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false )
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String password;

  @NotNull
  @Column(nullable = false)
  private String name;

  @NotNull
  @Column(nullable = false)
  private String email;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserStatus status;

  @NotNull
  @Column(nullable = false)
  private LocalDateTime createdAt;
}