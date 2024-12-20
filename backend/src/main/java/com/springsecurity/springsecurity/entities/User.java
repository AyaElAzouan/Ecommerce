package com.springsecurity.springsecurity.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = " users")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;
    private String password;
    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;


//    @OneToOne(mappedBy = "user")
//    private Admin admin;
//
//    @OneToOne(mappedBy = "user")
//    private Prof prof;
//
//    @OneToOne(mappedBy = "user")
//    private Etudiant etudiant;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
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
        return true ;
    }
}
