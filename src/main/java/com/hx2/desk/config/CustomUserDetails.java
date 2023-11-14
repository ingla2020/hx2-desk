package com.hx2.desk.config;

import com.hx2.desk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class CustomUserDetails implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                /*
                return userRepository.findByEmail(username)
                        .map(account -> {
                            account.setPassword(passwordEncoder.encode(account.getPassword()));
                            UserDetails userD = account;
                            return account;
                        }).orElseThrow(() -> new UsernameNotFoundException("Username doesn,t exists"));
                */
            }
        };
    }

}
