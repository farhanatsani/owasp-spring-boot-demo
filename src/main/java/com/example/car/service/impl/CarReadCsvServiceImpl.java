package com.example.car.service.impl;

import com.example.car.dto.CarDto;
import com.example.car.service.CarReadCsvService;
import com.example.base.utils.CarParserUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("readCsvCarBuffer")
public class CarReadCsvServiceImpl implements CarReadCsvService {
    @SneakyThrows
    @Override
    public List<CarDto> readCarCsv(MultipartFile file) {
        log.info("start read csv");
        long start = System.currentTimeMillis();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));

        List<CarDto> carList = new ArrayList<>();
        String line;
        while((line = bufferedReader.readLine()) != null) {
            String[] tempLine = line.split(",");
            CarDto carVO = CarParserUtils.parseToCarDTO(tempLine);
            carList.add(carVO);
        }
        bufferedReader.close();

        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        log.info("read csv elapsed time {} seconds", elapsed);
        return carList;
    }
}
