package com.example.jpa_exercise_relational_mapping.repository;

import com.example.jpa_exercise_relational_mapping.model.AppUser;
import com.example.jpa_exercise_relational_mapping.model.Car;
import com.example.jpa_exercise_relational_mapping.model.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends CrudRepository<Car,Integer> {

    //a. Find a Car that match registration number.
    Car findCarByRegNumberIgnoreCase(String regNumber);

    //b. Find all Car’s with a specific status code.
    List<Car> findCarByStatusCodesContainsIgnoreCase(Status statusCode);

    //c. Find all Car’s older than a specific date.
    List<Car> findCarByRegDateBefore(LocalDate date);

    //d. Find all Car’s newer than a specific date.
    List<Car> findCarByRegDateAfter(LocalDate date);

    //e. Find all Car’s registered between two dates.
    @Query("SELECT c FROM Car c WHERE c.regDate BETWEEN :startDate AND :endDate")
    List<Car> findCarByRegDateBetween(@Param("startDate") LocalDate start, @Param("endDate")LocalDate end);
}
