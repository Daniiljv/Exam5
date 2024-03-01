package com.jdbc.exam5.services;

import com.jdbc.exam5.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userToCreate);
    List<UserDto> findAll();
    UserDto findById(Long id);

    void delete(Long id);
}
