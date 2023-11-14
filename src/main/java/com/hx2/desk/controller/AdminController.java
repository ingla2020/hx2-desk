package com.hx2.desk.controller;

import com.hx2.desk.dto.UserDto;
import com.hx2.desk.model.User;
import com.hx2.desk.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secure")
public class AdminController {

    @Autowired
    public UserServices userservices;

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/add")
    public User addUserByAdmin(@RequestBody UserDto user) {

        return userservices.addUserByAdmin(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/test")
    public String test() {
        return "paso prueba";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
//@PreAuthorize("hasRole('USER')")
    @GetMapping("/user/all")
    public List<User> allUser() {
        return userservices.findAllUser();
    }

}
