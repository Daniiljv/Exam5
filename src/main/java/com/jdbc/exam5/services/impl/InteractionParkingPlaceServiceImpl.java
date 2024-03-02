package com.jdbc.exam5.services.impl;

import com.jdbc.exam5.dtos.CreateInteractionDto;
import com.jdbc.exam5.enums.Status;
import com.jdbc.exam5.entities.InteractionParkingPlaceEntity;
import com.jdbc.exam5.entities.ParkingPlaceEntity;
import com.jdbc.exam5.repo.InteractionParkingPlaceRepo;
import com.jdbc.exam5.repo.ParkingPlaceRepo;
import com.jdbc.exam5.repo.UserRepo;
import com.jdbc.exam5.services.InteractionParkingPlaceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InteractionParkingPlaceServiceImpl implements InteractionParkingPlaceService {

    private final InteractionParkingPlaceRepo interactionParkingPlaceRepo;
    private final UserRepo userRepo;
    private final ParkingPlaceRepo parkingPlaceRepo;

    @Override
    public String reservePlace(CreateInteractionDto reservePlace) throws RuntimeException{
        Long orderId;
        if (userRepo.findById(reservePlace.getUser().getId()).isPresent() &&
                parkingPlaceRepo.findAvailablePlaceById(reservePlace.getParkingPlace().getId()) != null) {

            try {
                InteractionParkingPlaceEntity reserve = InteractionParkingPlaceEntity.builder()
                        .parkingPlace(reservePlace.getParkingPlace())
                        .user(reservePlace.getUser())
                        .build();

                orderId = interactionParkingPlaceRepo.save(reserve).getId();
            } catch (Exception e) {
                throw new RuntimeException("Some thing went wrong!");
            }
            ParkingPlaceEntity reservedParkingPlace =
                    parkingPlaceRepo.findAvailablePlaceById(reservePlace.getParkingPlace().getId());

            reservedParkingPlace.setStatus(Status.RESERVED);
            parkingPlaceRepo.save(reservedParkingPlace);
            return "Your reservation is successful. Remember id for Confirming or Canceling " +
                    "ID: " + orderId;
        } else throw new RuntimeException("Some thing went wrong!");
    }

    @Override
    public String confirmReservationByOrderId(Long orderId) throws EntityNotFoundException{
        InteractionParkingPlaceEntity entity = interactionParkingPlaceRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order with id - " + orderId + " is not found"));

        ParkingPlaceEntity placeEntity = parkingPlaceRepo.findById(entity.getParkingPlace().getId())
                .orElseThrow(()->new EntityNotFoundException("Place with id - " + entity.getParkingPlace().getId() + " is not available"));
        placeEntity.setStatus(Status.NOT_AVAILABLE);
        parkingPlaceRepo.save(placeEntity);

        return "Your reservation has been successfully confirmed!";
    }


    @Override
    public String takePlace(CreateInteractionDto takePlace) throws RuntimeException{
        Long orderId;
        if (userRepo.findById(takePlace.getUser().getId()).isPresent() &&
                parkingPlaceRepo.findAvailablePlaceById(takePlace.getParkingPlace().getId()) != null) {

            try {
                InteractionParkingPlaceEntity reserve = InteractionParkingPlaceEntity.builder()
                        .parkingPlace(takePlace.getParkingPlace())
                        .user(takePlace.getUser())
                        .build();

                orderId = interactionParkingPlaceRepo.save(reserve).getId();
            } catch (Exception e) {
                throw new RuntimeException("Some thing went wrong");
            }
            ParkingPlaceEntity takeParkingPlace =
                    parkingPlaceRepo.findAvailablePlaceById(takePlace.getParkingPlace().getId());

                takeParkingPlace.setStatus(Status.NOT_AVAILABLE);
                parkingPlaceRepo.save(takeParkingPlace);

                return "Your interaction is successful. Remember id for Releasing Parking place " +
                        "ID: " + orderId;
        } else throw new RuntimeException("Some thing went wrong!");
    }

    @Override
    public String releaseParkingPlace(Long orderId) throws EntityNotFoundException{
        InteractionParkingPlaceEntity entity = interactionParkingPlaceRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order with id - " + orderId + " is not found"));
        ParkingPlaceEntity placeEntity = parkingPlaceRepo.findById(entity.getParkingPlace().getId())
                .orElseThrow(() -> new EntityNotFoundException("Place with id - " + orderId + " is not found"));

            placeEntity.setStatus(Status.AVAILABLE);
            parkingPlaceRepo.save(placeEntity);

        return "You successfully released the parking place!";
    }

}


