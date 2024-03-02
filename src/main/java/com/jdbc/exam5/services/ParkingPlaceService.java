package com.jdbc.exam5.services;

import com.jdbc.exam5.dtos.CreateParkingPlaceDto;
import com.jdbc.exam5.dtos.ParkingPlaceDto;
import com.jdbc.exam5.enums.ParkingType;

import java.util.List;

public interface ParkingPlaceService {
    List<ParkingPlaceDto> findAll();
    ParkingPlaceDto findById(Long id);
    List<ParkingPlaceDto> findAllAvailablePlaces();
    List<ParkingPlaceDto> findAvailablePlacesByParkingType(ParkingType parkingType);

    CreateParkingPlaceDto create(CreateParkingPlaceDto parkingPlaceToCreate);

    void delete(Long id);
}
