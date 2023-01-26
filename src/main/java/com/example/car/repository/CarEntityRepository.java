package com.example.car.repository;

import com.example.car.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarEntityRepository extends JpaRepository<CarEntity, UUID> {
    List<CarEntity> findByCarBrandIgnoreCase(String carBrand);
}
