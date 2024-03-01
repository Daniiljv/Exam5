package com.jdbc.exam5.dtos;

import com.jdbc.exam5.entities.ParkingPlaceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private List<ParkingPlaceEntity> parkingPlaces;
}
