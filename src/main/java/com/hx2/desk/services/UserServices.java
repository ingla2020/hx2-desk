package com.hx2.desk.services;

import com.hx2.desk.dto.UserDto;
import com.hx2.desk.model.User;

import java.util.List;

public interface UserServices {
    User addUserByAdmin(UserDto user);

    List<User> findAllUser();
}
