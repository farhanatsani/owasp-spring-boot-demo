package com.example.car.entity;

import com.example.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CarEntity extends BaseEntity {
    private String carBrand;
    private String carType;
    private String carYear;
    private BigDecimal sellingPrice;
    private String kmDriven;
    private String fuelType;
    private String transmission;
    private String vin;
    private String description;
}
