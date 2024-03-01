package com.jdbc.exam5.services.impl;

import com.jdbc.exam5.dtos.UserDto;
import com.jdbc.exam5.entities.UserEntity;
import com.jdbc.exam5.repo.UserRepo;
import com.jdbc.exam5.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo repo;
    @Override
    public UserDto create(UserDto userToCreate) {
        UserEntity userEntity = UserEntity.builder()
                .id(userToCreate.getId())
                .name(userToCreate.getName())
                .surname(userToCreate.getSurname())
                .parkingPlaces(userToCreate.getParkingPlaces())
                .build();
        try{
            repo.save(userEntity);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return userToCreate;
    }

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> userEntities = repo.findAll();

        List<UserDto> userDtoList = new ArrayList<>();
        for(UserEntity user : userEntities){
            UserDto userDto = UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .surname(user.getSurname())
                    .parkingPlaces(user.getParkingPlaces())
                    .build();
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public UserDto findById(Long id) {
        UserEntity userEntity = repo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(""));

        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .parkingPlaces(userEntity.getParkingPlaces())
                .build();
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
