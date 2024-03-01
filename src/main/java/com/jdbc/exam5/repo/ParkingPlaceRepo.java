package com.jdbc.exam5.repo;

import com.jdbc.exam5.dtos.ParkingPlaceDto;
import com.jdbc.exam5.enams.ParkingType;
import com.jdbc.exam5.enams.Status;
import com.jdbc.exam5.entities.ParkingPlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ParkingPlaceRepo extends JpaRepository<ParkingPlaceEntity, Long> {
 @Query("select p from ParkingPlaceEntity p " +
         "where status = :status")
    List<ParkingPlaceEntity> findAllNotReserved(@Param("status")Status status);

 @Query("select p from ParkingPlaceEntity p " +
         "where p.parkingType = :type ")
    List<ParkingPlaceEntity> findParkingPlaceByType(@Param("type")ParkingType type);


}
