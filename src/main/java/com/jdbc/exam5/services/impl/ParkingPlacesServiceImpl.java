package com.jdbc.exam5.services.impl;

import com.jdbc.exam5.dtos.CreateParkingPlaceDto;
import com.jdbc.exam5.dtos.ParkingPlaceDto;
import com.jdbc.exam5.enums.Status;
import com.jdbc.exam5.entities.ParkingPlaceEntity;
import com.jdbc.exam5.repo.InteractionParkingPlaceRepo;
import com.jdbc.exam5.repo.ParkingPlaceRepo;
import com.jdbc.exam5.enums.ParkingType;
import com.jdbc.exam5.services.ParkingPlaceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingPlacesServiceImpl implements ParkingPlaceService {

    private final ParkingPlaceRepo repo;
    private final InteractionParkingPlaceRepo interactionParkingPlaceRepo;
    @Override
    public List<ParkingPlaceDto> findAll() {
        List<ParkingPlaceEntity> parkingPlaceEntities = repo.findAll();

        List<ParkingPlaceDto> parkingPlaceDtoList = new ArrayList<>();
        for(ParkingPlaceEntity parkingPlace : parkingPlaceEntities){
            ParkingPlaceDto parkingPlaceDto = ParkingPlaceDto.builder()
                    .id(parkingPlace.getId())
                    .spotNumber(parkingPlace.getSpotNumber())
                    .parkingType(parkingPlace.getParkingType())
                    .status(parkingPlace.getStatus())
                    .build();
            parkingPlaceDtoList.add(parkingPlaceDto);
        }
        return parkingPlaceDtoList;
    }

    @Override
    public ParkingPlaceDto findById(Long id) {
        ParkingPlaceEntity parkingPlaceEntity = repo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Parking place is not found"));

        return ParkingPlaceDto.builder()
                .id(parkingPlaceEntity.getId())
                .spotNumber(parkingPlaceEntity.getSpotNumber())
                .parkingType(parkingPlaceEntity.getParkingType())
                .status(parkingPlaceEntity.getStatus())
                .build();
    }

    @Override
    public List<ParkingPlaceDto> findAllAvailablePlaces() {
        List<ParkingPlaceEntity> parkingPlaceEntities = repo.findAllAvailablePlaces();

        List<ParkingPlaceDto> parkingPlaceDtoList = new ArrayList<>();
        for(ParkingPlaceEntity parkingPlace : parkingPlaceEntities){
            ParkingPlaceDto parkingPlaceDto = ParkingPlaceDto.builder()
                    .id(parkingPlace.getId())
                    .spotNumber(parkingPlace.getSpotNumber())
                    .parkingType(parkingPlace.getParkingType())
                    .status(parkingPlace.getStatus())
                    .build();
            parkingPlaceDtoList.add(parkingPlaceDto);
        }
        return parkingPlaceDtoList;

    }

    @Override
    public List<ParkingPlaceDto> findAvailablePlacesByParkingType(ParkingType parkingType) {
        List<ParkingPlaceEntity> parkingPlaceEntities = repo.findAvailableParkingPlaceByType(parkingType);

        List<ParkingPlaceDto> parkingPlaceDtoList = new ArrayList<>();
        for(ParkingPlaceEntity parkingPlace : parkingPlaceEntities){
            ParkingPlaceDto parkingPlaceDto = ParkingPlaceDto.builder()
                    .id(parkingPlace.getId())
                    .spotNumber(parkingPlace.getSpotNumber())
                    .parkingType(parkingPlace.getParkingType())
                    .status(parkingPlace.getStatus())
                    .build();
            parkingPlaceDtoList.add(parkingPlaceDto);
        }
        return parkingPlaceDtoList;
    }

    @Override
    public CreateParkingPlaceDto create(CreateParkingPlaceDto parkingPlaceToCreate) throws RuntimeException{
        try{ ParkingPlaceEntity parkingPlaceEntity = ParkingPlaceEntity.builder()
                .spotNumber(parkingPlaceToCreate.getSpotNumber())
                .parkingType(parkingPlaceToCreate.getParkingType())
                .status(Status.AVAILABLE)
                .build();
            repo.save(parkingPlaceEntity);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return parkingPlaceToCreate;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        ParkingPlaceEntity parkingPlace = repo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Parking place is not found"));
        interactionParkingPlaceRepo.deleteParkingPlace(parkingPlace);
        repo.deleteById(parkingPlace.getId());
    }
}
