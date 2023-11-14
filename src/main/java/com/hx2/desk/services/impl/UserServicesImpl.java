package com.hx2.desk.services.impl;

import com.hx2.desk.dto.UserDto;
import com.hx2.desk.enums.Role;
import com.hx2.desk.model.User;
import com.hx2.desk.repository.UserRepository;
import com.hx2.desk.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encodePWD;

    @Override
    public User addUserByAdmin(UserDto user) {

        //user.setTransactionId(UUID.randomUUID().toString());

        String pwd = user.getPassword();
        String encryptPwd = encodePWD.encode(pwd);
        User us = new User();
        us.setEmail(user.getEmail());
        us.setUsername(user.getEmail());
        us.setFirstName(user.getFirstName());
        us.setLastName(user.getLastName());
        us.setPassword(encryptPwd);
        us.setRole(Role.valueOf(user.getRole()));
        us = userRepository.save(us);
        return us;

    }
    @Override
    public List<User> findAllUser() {

        //	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
        //            .getPrincipal();
        //	String username = userDetails.getUsername();
        String username = "leo@gmail.com";

        return userRepository.findAll()
                .stream()
                .filter(user -> !user.getEmail().equals(username))
                .collect(Collectors.toList());
    }
//	@Override
//	public Optional<User> findByUser(String user) {
//		return userRepository.fin .findByUser(user);
//	}
}
