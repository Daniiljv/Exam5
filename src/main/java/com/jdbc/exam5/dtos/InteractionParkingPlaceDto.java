package com.jdbc.exam5.dtos;

import com.jdbc.exam5.entities.ParkingPlaceEntity;
import com.jdbc.exam5.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InteractionParkingPlaceDto {

    private Long id;
    private UserEntity user;
    private ParkingPlaceEntity parkingPlace;

}
