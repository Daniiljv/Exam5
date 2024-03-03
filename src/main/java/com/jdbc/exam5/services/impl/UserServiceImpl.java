package com.jdbc.exam5.services.impl;

import com.jdbc.exam5.dtos.CreateUserDto;
import com.jdbc.exam5.dtos.UserDto;
import com.jdbc.exam5.entities.UserEntity;
import com.jdbc.exam5.repo.InteractionParkingPlaceRepo;
import com.jdbc.exam5.repo.UserRepo;
import com.jdbc.exam5.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo repo;
    private final InteractionParkingPlaceRepo interactionParkingPlaceRepo;
    @Override
    public CreateUserDto create(CreateUserDto userToCreate) throws RuntimeException{
        UserEntity userEntity = UserEntity.builder()
                .name(userToCreate.getName())
                .surname(userToCreate.getSurname())
                .build();
        try{
            UserEntity entity = repo.save(userEntity);
            userToCreate.setId(entity.getId());
        }catch (Exception e){
            throw new RuntimeException();
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
                    .build();
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public UserDto findById(Long id) {
        UserEntity userEntity = repo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User is not found"));

        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .build();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        UserEntity user = repo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User is not found!"));
        interactionParkingPlaceRepo.deleteUser(user);
        repo.deleteById(user.getId());
    }
}
