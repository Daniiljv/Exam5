package com.jdbc.exam5.repo;

import com.jdbc.exam5.enums.ParkingType;
import com.jdbc.exam5.entities.ParkingPlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ParkingPlaceRepo extends JpaRepository<ParkingPlaceEntity, Long> {
 @Query("select p from ParkingPlaceEntity p " +
         "where p.status = com.jdbc.exam5.enums.Status.AVAILABLE")
    List<ParkingPlaceEntity> findAllAvailablePlaces();

 @Query("select p from ParkingPlaceEntity p " +
         "where p.parkingType = :type " +
         "and p.status = com.jdbc.exam5.enums.Status.AVAILABLE")
    List<ParkingPlaceEntity> findAvailableParkingPlaceByType(@Param("type")ParkingType type);

 @Query("select p from  ParkingPlaceEntity p " +
         "where p.status = com.jdbc.exam5.enums.Status.AVAILABLE and p.id = :id")
    ParkingPlaceEntity findAvailablePlaceById(@Param("id")Long id);

}
