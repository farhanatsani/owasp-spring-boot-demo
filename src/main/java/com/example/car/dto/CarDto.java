package com.example.car.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CarDto {
    private UUID id;
    private String carBrand;
    private String carType;
    private String year;
    private BigDecimal sellingPrice;
    private String kmDriven;
    private String fuelType;
    private String transmission;
    private String vin;
    private String description;
}
