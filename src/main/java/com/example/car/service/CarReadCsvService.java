package com.example.car.service;

import com.example.car.dto.CarDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarReadCsvService {
    List<CarDto> readCarCsv(MultipartFile file);
}
