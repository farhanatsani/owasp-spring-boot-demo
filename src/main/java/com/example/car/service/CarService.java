package com.example.car.service;

import com.example.car.dto.CarDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarService {
    CarDto saveCar(CarDto carDto);
    List<CarDto> findAllCars();
    Optional<CarDto> findOneCarById(UUID id);
    List<CarDto> findCarByBrand(String brand);
    List<CarDto> importCars(MultipartFile file);
}
