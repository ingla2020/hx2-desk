package com.code.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.code.enums.Role;
import java.util.Collection;
import java.util.List;


    @Entity
    @Table(name = "_user")
    @Data
    public class User implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String username;
        private String password;
        @Column(unique=true)
        private String email;
        private String firstName;
        private String lastName;
        @Enumerated(EnumType.STRING)
        private Role role;
        private String transactionId;


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority(role.name()));
        }

        @Override
        public String getUsername() {
            // email in our case
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
            return true;
        }
    }
