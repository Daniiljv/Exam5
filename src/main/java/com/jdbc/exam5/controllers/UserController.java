package com.jdbc.exam5.controllers;


import com.jdbc.exam5.dtos.CreateUserDto;
import com.jdbc.exam5.dtos.UserDto;
import com.jdbc.exam5.services.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("users/")
public class UserController {

    private final UserServiceImpl service;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User has been created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateUserDto.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Failed to add user to database")
    })
    @Operation(summary = "This road creates user")
    @PostMapping("create")
    public ResponseEntity<CreateUserDto> create(@RequestBody CreateUserDto userToCreate){
       try {
           return new ResponseEntity<>(service.create(userToCreate), HttpStatus.CREATED);
       }catch (RuntimeException runtimeException){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The list of users is not empty",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any user")
    })
    @Operation(summary = "This road shows all users")
    @GetMapping("findAll")
    public ResponseEntity<List<UserDto>> findAll(){
       List<UserDto> userDtoList = service.findAll();
       if(!userDtoList.isEmpty()){
           return new ResponseEntity<>(userDtoList, HttpStatus.OK);
       }else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The user exists",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any user with this id")
    })
    @Operation(summary = "This road show user by id")
    @GetMapping("findById/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        }catch (EntityNotFoundException notFoundException){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The user was deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any user with this id")
    })
    @Operation(summary = "This road deletes user by id")
    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteById(@RequestParam Long id){
        try{
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (EntityNotFoundException notFoundException){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
