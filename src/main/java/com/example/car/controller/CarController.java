package com.example.car.controller;

import com.example.base.constants.RspMsgConstants;
import com.example.base.rest.BaseController;
import com.example.base.rest.BaseResponseDTO;
import com.example.car.dto.CarDto;
import com.example.car.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/api/cars")
@Validated
public class CarController extends BaseController {
    private CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(
            summary = "Save a new car",
            tags = {"Cars-API"}
    )
    @ResponseStatus(CREATED)
    @PostMapping
    public ResponseEntity<BaseResponseDTO> saveCar(@Valid @RequestBody CarDto carDto) {
        return ResponseEntity.status(CREATED)
                .body(createResponse(
                        carService.saveCar(carDto),
                        OK.value(),
                        RspMsgConstants.constructMessage(carDto.getCarBrand(), RspMsgConstants.$_SUCCESSFULLY_SAVE))
                );
    }

    @Operation(
            summary = "Retrieves list of cars",
            tags = {"Cars-API"}
    )
    @ResponseStatus(OK)
    @GetMapping
    public ResponseEntity<BaseResponseDTO> findCars() {
        List<CarDto> carDtoList = carService.findAllCars();

        if(carDtoList.isEmpty()) {
            throw new NullPointerException(RspMsgConstants.DATA_NOT_AVAILABLE);
        }

        return ResponseEntity.status(OK)
                .body(
                        createResponse(
                                carDtoList,
                                carDtoList.size(),
                                OK.value(),
                                RspMsgConstants.DATA_AVAILABLE)
                );
    }

    @Operation(
            summary = "Retrieves 1 Car By Id",
            tags = {"Cars-API"}
    )
    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDTO> findCarById(@PathVariable("id") UUID id) {
        Optional<CarDto> carOptional = carService.findOneCarById(id);

        if(carOptional.isEmpty()) {
            throw new NullPointerException(RspMsgConstants.DATA_NOT_AVAILABLE);
        }

        return ResponseEntity.status(OK)
                .body(
                        createResponse(
                                carOptional.get(),
                                OK.value(),
                                RspMsgConstants.DATA_AVAILABLE)
                );
    }

    @Operation(
            summary = "Retrieves 1 Car By Brand",
            tags = {"Cars-API"}
    )
    @ResponseStatus(OK)
    @GetMapping("/search")
    public ResponseEntity<BaseResponseDTO> findCarByBrand(@RequestParam("brand") String brand) {
        List<CarDto> carDtoList = carService.findCarByBrand(brand);

        if(carDtoList.isEmpty()) {
            throw new NullPointerException(RspMsgConstants.DATA_NOT_AVAILABLE);
        }

        return ResponseEntity.status(OK)
                .body(
                        createResponse(
                                carDtoList,
                                carDtoList.size(),
                                OK.value(),
                                RspMsgConstants.DATA_AVAILABLE)
                );
    }

    @Operation(
            summary = "Uploads bookmarks inside an excel sheet",
            tags = {"Bookmark-API"}
    )
    @ResponseStatus(OK)
    @PostMapping("/upload")
    public ResponseEntity<BaseResponseDTO> uploadCars(@RequestParam("file") MultipartFile file) {
        List<CarDto> carList = carService.importCars(file);
        return ResponseEntity.status(OK).body(createResponse(
                null,
                carList.size(),
                OK.value(),
                RspMsgConstants.constructMessage(
                        String.valueOf(carList.size()),
                        RspMsgConstants.SUCCESSFULLY_SAVE_$
                )
        ));
    }

}
