package com.example.base.utils;

import com.example.car.dto.CarDto;

import java.math.BigDecimal;

public class CarParserUtils {

    public static CarDto parseToCarDTO(String[] lines) {
        String brand = lines[0];
        if(brand.equalsIgnoreCase("Skoda")) {
            brand = "Škoda";
        } else if(brand.equalsIgnoreCase("Chevy")) {
            brand = "Chevrolet";
        } else if(brand.equalsIgnoreCase("Xpeng")) {
            brand = "XPeng";
        }

        String type = lines[1];
        if(type.equalsIgnoreCase("Model")) {
            type = "Model Y";
        } else if(type.equalsIgnoreCase("G3i")) {
            type = "G3";
        } else if(type.equalsIgnoreCase("ES7")) {
            type = "ET7";
        } else if(type.equalsIgnoreCase("Camero")) {
            type = "Camaro";
        } else if(type.equalsIgnoreCase("Riveria")) {
            type = "Riviera";
        } else if(type.equalsIgnoreCase("IONIQ")) {
            type = "Ioniq";
        } else if(type.equalsIgnoreCase("Pathfiner")) {
            type = "Pathfinder";
        }

        CarDto carVO = CarDto.builder()
                .carBrand(brand)
                .carType(type)
                .year(lines[2])
                .sellingPrice(new BigDecimal(lines[3]))
                .kmDriven(lines[4])
                .fuelType(lines[5])
                .transmission(lines[6])
                .vin(lines[7])
                .build();
        return carVO;
    }

}
