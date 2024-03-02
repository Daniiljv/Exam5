package com.jdbc.exam5.entities;

import com.jdbc.exam5.enums.ParkingType;
import com.jdbc.exam5.enums.Status;
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
   @Column(unique = true)
   private String spotNumber;
   private ParkingType parkingType;
   private Status status;
}
