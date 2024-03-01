package com.jdbc.exam5.services;

import com.jdbc.exam5.dtos.ParkingPlaceDto;
import com.jdbc.exam5.enams.ParkingType;

import java.util.List;

public interface ParkingPlaceService {
    List<ParkingPlaceDto> findAll();
    ParkingPlaceDto findById(Long id);
    List<ParkingPlaceDto> findNotReservedPlaces();
    List<ParkingPlaceDto> findPlacesByParkingType(ParkingType parkingType);

    ParkingPlaceDto create(ParkingPlaceDto parkingPlaceToCreate);

    void delete(Long id);

}
