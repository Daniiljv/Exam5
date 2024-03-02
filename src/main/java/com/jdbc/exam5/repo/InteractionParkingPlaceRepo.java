package com.jdbc.exam5.repo;

import com.jdbc.exam5.entities.InteractionParkingPlaceEntity;
import com.jdbc.exam5.entities.ParkingPlaceEntity;
import com.jdbc.exam5.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InteractionParkingPlaceRepo extends JpaRepository<InteractionParkingPlaceEntity, Long> {
    @Modifying
    @Query("delete from InteractionParkingPlaceEntity ipp " +
           "where ipp.user = :user")
    void deleteUser(@Param("user") UserEntity user);

    @Modifying
    @Query("delete from InteractionParkingPlaceEntity ipp " +
            "where ipp.parkingPlace = :parkingPlace")
    void deleteParkingPlace(@Param("parkingPlace") ParkingPlaceEntity parkingPlace);

}
