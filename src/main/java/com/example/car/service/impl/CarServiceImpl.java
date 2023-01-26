package com.example.car.service.impl;

import com.example.car.dto.CarDto;
import com.example.car.entity.CarEntity;
import com.example.car.repository.CarEntityRepository;
import com.example.car.service.CarReadCsvService;
import com.example.car.service.CarService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private CarEntityRepository carEntityRepository;
    private CarReadCsvService carReadCsvService;

    public CarServiceImpl(CarEntityRepository carEntityRepository,
                          @Qualifier(value = "readCsvCarBuffer") CarReadCsvService carReadCsvService) {

        this.carEntityRepository = carEntityRepository;
        this.carReadCsvService = carReadCsvService;
    }

    @Override
    public CarDto saveCar(CarDto carDto) {
        CarEntity carEntity = carEntityRepository.save(toEntity(carDto));
        return toCarDto(carEntity);
    }

    @Override
    public List<CarDto> findAllCars() {
        return carEntityRepository.findAll()
                .stream()
                .map(this::toCarDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CarDto> findOneCarById(UUID id) {
        return carEntityRepository.findById(id).map(this::toCarDto);
    }

    @Override
    public List<CarDto> findCarByBrand(String brand) {
        return carEntityRepository.findByCarBrandIgnoreCase(brand)
                .stream()
                .map(this::toCarDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDto> importCars(MultipartFile file) {
        List<CarDto> carDtoList = carReadCsvService.readCarCsv(file);
        List<CarEntity> carEntities = carEntityRepository.saveAllAndFlush(
                carDtoList.stream().map(this::toEntity).collect(Collectors.toList())
        );
        return carEntities.stream().map(this::toCarDto).collect(Collectors.toList());
    }

    private CarDto toCarDto(CarEntity car) {
        return CarDto.builder()
                .id(car.getId())
                .carBrand(car.getCarBrand())
                .carType(car.getCarType())
                .year(car.getCarYear())
                .sellingPrice(car.getSellingPrice())
                .kmDriven(car.getKmDriven())
                .fuelType(car.getFuelType())
                .transmission(car.getTransmission())
                .vin(car.getVin())
                .description(car.getDescription())
                .build();
    }

    private CarEntity toEntity(CarDto carDto) {
        return CarEntity.builder()
                .id(carDto.getId())
                .carBrand(carDto.getCarBrand())
                .carType(carDto.getCarType())
                .carYear(carDto.getYear())
                .sellingPrice(carDto.getSellingPrice())
                .kmDriven(carDto.getKmDriven())
                .fuelType(carDto.getFuelType())
                .transmission(carDto.getTransmission())
                .vin(carDto.getVin())
                .description(carDto.getDescription())
                .build();
    }

}
