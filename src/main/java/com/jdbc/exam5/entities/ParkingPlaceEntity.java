package com.jdbc.exam5.entities;

import com.jdbc.exam5.enams.ParkingType;
import com.jdbc.exam5.enams.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "parking_place")
public class ParkingPlaceEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String spotNumber;
   private ParkingType parkingType;
   private Status status;
}
