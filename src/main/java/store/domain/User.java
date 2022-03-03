package store.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import store.domain.enumeration.LanguageCode;
import store.domain.enumeration.Role;

@Entity(name = "users")
@Table(name = "users", indexes = {
        @Index(name = "email_index", columnList = "email", unique = true) })
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User implements UserDetails, Serializable{

    @Column(columnDefinition = "bigserial")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @SequenceGenerator(name = "users_sequence", sequenceName = "users_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(nullable = false)
    @NotNull
    private boolean deleted = false;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 128)
    private String firstName;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 128)
    private String lastName;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 128)
    private String email;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 255)
    private String hashPassword;

    @Builder.Default
    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private LanguageCode languageCode = LanguageCode.en;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 255)
    private String country;

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Size(max = 1024)
    private String subscriptions;

    @Column(nullable = false)
    @Size(max = 255)
    private String sessionId;

    @NotNull
    @CreationTimestamp
    private LocalDateTime lastLogin = LocalDateTime.now();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {return this.hashPassword;}

    @Override
    public String getUsername() {
        return this.email;
    }
}
