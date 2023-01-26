package com.example;

import com.example.car.entity.CarEntity;
import com.example.car.repository.CarEntityRepository;
import com.example.user.entity.UserEntity;
import com.example.user.repository.UserEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class InitData implements CommandLineRunner {

    private UserEntityRepository userEntityRepository;
    private CarEntityRepository carEntityRepository;
    private PasswordEncoder passwordEncoder;

    public InitData(UserEntityRepository userEntityRepository, CarEntityRepository carEntityRepository, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.carEntityRepository = carEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        createUsers();
        saveCars();
    }

    private void createUsers() {
        log.info("Create initialize users");
        List<UserEntity> userEntities = Stream.of(
                        new UserEntity("Agus", "A", passwordEncoder.encode("agus"), "agus@email.com", List.of("USER")),
                        new UserEntity("John", "Doe", passwordEncoder.encode("john"), "john@email.com", List.of("ADMIN")),
                        new UserEntity("Udin", "G", passwordEncoder.encode("udin"), "udin@email.com", List.of("USER")))
                .map(userEntityRepository::save)
                .collect(Collectors.toList());
        log.info("Finish created {} users", userEntities.size());
    }

    private void saveCars() {
        log.info("Create initialize cars");
        List<CarEntity> carEntities = Stream.of(
            new CarEntity("Toyota", "Vios G", "2008", new BigDecimal("100000000"), "160000", "Bensin", "A/T", "123-123", ""),
            new CarEntity("Toyota", "Innova V", "2017", new BigDecimal("250000000"), "190000", "Diesel", "A/T", "125-125", ""),
            new CarEntity("Honda", "CRV", "2019", new BigDecimal("350000000"), "60000", "Bensin", "A/T", "121-129", ""))
                .map(carEntityRepository::save)
                .collect(Collectors.toList());
        log.info("Finish created {} cars", carEntities.size());
    }

}
