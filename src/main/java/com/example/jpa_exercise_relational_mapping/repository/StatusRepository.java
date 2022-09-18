package com.example.jpa_exercise_relational_mapping.repository;

import com.example.jpa_exercise_relational_mapping.model.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status,Integer> {

}
