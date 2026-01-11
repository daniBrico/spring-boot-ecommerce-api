package ecommerce_java_springboot.models;

import ecommerce_java_springboot.models.enums.Role;
import ecommerce_java_springboot.models.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
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
  @Column(nullable = false, unique = true)
  private String email;

  @NotNull
  @Builder.Default
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role = Role.CUSTOMER;

  @NotNull
  @Builder.Default
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserStatus status = UserStatus.ACTIVE;

  @NotNull
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<TokenModel> tokens;
}