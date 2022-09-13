package com.example.jpa_exercise_relational_mapping.dao;

import com.example.jpa_exercise_relational_mapping.model.Car;
import com.example.jpa_exercise_relational_mapping.model.Status;

import java.util.List;
import java.util.Optional;

public interface StatusDAO {

    Status save(Status status);

    Optional<Status> findById(int id);

    List<Status> findAll();

    void remove(int statusId);
}
