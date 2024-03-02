package com.jdbc.exam5.controllers;

import com.jdbc.exam5.dtos.CreateParkingPlaceDto;
import com.jdbc.exam5.dtos.ParkingPlaceDto;
import com.jdbc.exam5.enums.ParkingType;
import com.jdbc.exam5.services.impl.ParkingPlacesServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("parking-spots/")
public class ParkingPlaceController {

    private final ParkingPlacesServiceImpl service;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "You have created parking place successfully ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateParkingPlaceDto.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Failed to add place to database")
    })
    @Operation(summary = "This road creates parking place")
    @PostMapping("create")
    public ResponseEntity<CreateParkingPlaceDto> createParkingPlace(@RequestBody CreateParkingPlaceDto parkingPlaceDto){
        try{
            return new ResponseEntity<>(service.create(parkingPlaceDto), HttpStatus.CREATED);
        }catch (RuntimeException runtimeException){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The list of parking places is not empty",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ParkingPlaceDto.class)))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any parking places")
    })
    @Operation(summary = "This road creates parking place")
    @GetMapping("findAll")
    public ResponseEntity<List<ParkingPlaceDto>> findAll(){
        List<ParkingPlaceDto> parkingPlaceDtoList = service.findAll();
        if(!parkingPlaceDtoList.isEmpty()){
            return new ResponseEntity<>(parkingPlaceDtoList, HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The parking place exists",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ParkingPlaceDto.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any parking place")
    })
    @Operation(summary = "This road can find parking place by id")
    @GetMapping("findById/{id}")
    public ResponseEntity<ParkingPlaceDto> findById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK) ;
        }catch (EntityNotFoundException notFoundException){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The list of parking places is not empty",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ParkingPlaceDto.class)))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any available parking places")
    })
    @Operation(summary = "This road shows all available parking places")
    @GetMapping("findAllAvailablePlaces")
    public ResponseEntity<List<ParkingPlaceDto>> findAllAvailablePlaces(){
        List<ParkingPlaceDto> parkingPlaceDtoList = service.findAllAvailablePlaces();
        if(!parkingPlaceDtoList.isEmpty()){
            return new ResponseEntity<>(parkingPlaceDtoList, HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The list of parking places is not empty",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ParkingPlaceDto.class)))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any available parking places")
    })
    @Operation(summary = "This road shows all available parking places with Standard type")
    @GetMapping("findAllAvailableStandardPlaces")
    public ResponseEntity<List<ParkingPlaceDto>> findAllAvailableStandardPlaces(){
        List<ParkingPlaceDto> parkingPlaceDtoList = service.findAvailablePlacesByParkingType(ParkingType.STANDARD);
        if(!parkingPlaceDtoList.isEmpty()){
            return new ResponseEntity<>(parkingPlaceDtoList, HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The list of parking places is not empty",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ParkingPlaceDto.class)))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any available parking places")
    })
    @Operation(summary = "This road shows all available parking places with ForDisabled people")
    @GetMapping("findAllAvailablePlacesForDisabled")
    public ResponseEntity<List<ParkingPlaceDto>> findAllAvailablePlacesForDisabled(){
        List<ParkingPlaceDto> parkingPlaceDtoList = service.findAvailablePlacesByParkingType(ParkingType.FOR_DISABLED);
        if(!parkingPlaceDtoList.isEmpty()){
            return new ResponseEntity<>(parkingPlaceDtoList, HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The list of parking places is not empty",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ParkingPlaceDto.class)))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any available parking places")
    })
    @Operation(summary = "This road shows all available parking places with Families cars")
    @GetMapping("findAllAvailablePlacesForFamilies")
    public ResponseEntity<List<ParkingPlaceDto>> findAllAvailablePlacesForFamilies(){
        List<ParkingPlaceDto> parkingPlaceDtoList = service.findAvailablePlacesByParkingType(ParkingType.FAMILIES);
        if(!parkingPlaceDtoList.isEmpty()){
            return new ResponseEntity<>(parkingPlaceDtoList, HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The list of parking places is not empty",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ParkingPlaceDto.class)))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any available parking places")
    })
    @Operation(summary = "This road shows all available parking places for Electric Cars")
    @GetMapping("findAllAvailablePlacesForElectricCars")
    public ResponseEntity<List<ParkingPlaceDto>> findAllAvailablePlacesForElectricCars(){
        List<ParkingPlaceDto> parkingPlaceDtoList = service.findAvailablePlacesByParkingType(ParkingType.ELECTRIC_CARS);
        if(!parkingPlaceDtoList.isEmpty()){
            return new ResponseEntity<>(parkingPlaceDtoList, HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The parking place was deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any parking places with this id")
    })
    @Operation(summary = "This road deletes parking place by id")
    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteById(@RequestParam Long id){
        try{
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (EntityNotFoundException notFoundException){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
