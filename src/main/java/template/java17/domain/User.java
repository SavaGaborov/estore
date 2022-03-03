package template.java17.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import template.java17.domain.enumeration.LanguageCode;
import template.java17.domain.enumeration.Role;

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
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 255)
    private String permissions;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 255)
    private String sessionId;

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
