package com.jdbc.exam5.controllers;


import com.jdbc.exam5.dtos.UserDto;
import com.jdbc.exam5.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("users/")
public class UserController {

    private final UserServiceImpl service;

    @PostMapping("create")
    public UserDto create(UserDto userToCreate){
        return service.create(userToCreate);
    }

    @GetMapping("findAll")
    public List<UserDto> findAll(){
        return service.findAll();
    }

    @GetMapping("findById/{id}")
    public UserDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("delete")
    public void deleteById(@RequestParam Long id){
        service.delete(id);
    }
}
