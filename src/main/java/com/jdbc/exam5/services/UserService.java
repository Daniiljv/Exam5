package com.jdbc.exam5.services;

import com.jdbc.exam5.dtos.CreateUserDto;
import com.jdbc.exam5.dtos.UserDto;

import java.util.List;

public interface UserService {
    CreateUserDto create(CreateUserDto userToCreate);
    List<UserDto> findAll();
    UserDto findById(Long id);

    void delete(Long id);
}
