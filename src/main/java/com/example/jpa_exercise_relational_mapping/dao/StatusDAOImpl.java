package com.example.jpa_exercise_relational_mapping.dao;

import com.example.jpa_exercise_relational_mapping.model.Status;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class StatusDAOImpl implements StatusDAO{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public Status save(Status status) {
        if (status == null){
            throw  new IllegalArgumentException("Status not allowed to be null");
        }
        entityManager.persist(status);
        return status;
    }

    @Override
    public Optional<Status> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Status> findAll() {
        return null;
    }

    @Override
    public void remove(int statusId) {

    }
}
