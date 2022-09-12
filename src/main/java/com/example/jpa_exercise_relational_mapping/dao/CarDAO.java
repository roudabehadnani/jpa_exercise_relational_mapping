package com.example.jpa_exercise_relational_mapping.dao;

import com.example.jpa_exercise_relational_mapping.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarDAO {

    Car save(Car car);

    Optional<Car> findById(int id);

    List<Car> findAll();

    void remove(int carId);
}
