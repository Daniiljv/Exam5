package com.jdbc.exam5.dtos;

import com.jdbc.exam5.enums.ParkingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateParkingPlaceDto {
    private Long id;
    private String spotNumber;
    private ParkingType parkingType;
}
