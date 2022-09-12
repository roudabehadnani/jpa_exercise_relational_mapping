package com.example.jpa_exercise_relational_mapping.dao;

import com.example.jpa_exercise_relational_mapping.model.Car;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CarDAOImpl implements CarDAO{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public Car save(Car car) {
        if (car == null){
            throw  new IllegalArgumentException("Car not allowed to be null");
        }
        entityManager.persist(car);
        return car;
    }

    @Override
    public Optional<Car> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public void remove(int carId) {

    }
}
