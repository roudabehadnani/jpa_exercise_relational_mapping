package com.example.jpa_exercise_relational_mapping.repository;

import com.example.jpa_exercise_relational_mapping.model.Address;
import com.example.jpa_exercise_relational_mapping.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    public List<Car> cars(){
        return Arrays.asList(
                new Car("AFR 124", "Volvo",LocalDate.parse("2019-05-01")),
                new Car("NMK 321", "BMW",LocalDate.parse("2020-08-01")),
                new Car("PLO987", "Skoda",LocalDate.parse("2020-03-05"))
        );
    }

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    CarRepository testRepo;

    Car testCar;

    @BeforeEach
    void setUp() {
        List<Car> persistedCars = cars().stream()
                .map(entityManager:: persist)
                .collect(Collectors.toList());
        testCar = persistedCars.get(0);
    }

    @Test
    void findCarByRegDateBetween() {
        LocalDate start = LocalDate.parse("2019-01-01");
        LocalDate end = LocalDate.parse("2020-05-01");
        List<Car> found = testRepo.findCarByRegDateBetween(start,end);
        assertEquals(2,found.size());

    }

    @Test
    void findByRegNumber(){
        String num = "PLO987";
        Car found = testRepo.findCarByRegNumberIgnoreCase(num);
        assertEquals("Skoda", found.getBrand());

    }

    @Test
    void findByRegDateBefore(){
        LocalDate date = LocalDate.parse("2020-01-01");
        List<Car> found = testRepo.findCarByRegDateBefore(date);
        assertEquals(1,found.size());
    }

}