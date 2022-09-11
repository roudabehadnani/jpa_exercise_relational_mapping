package com.example.jpa_exercise_relational_mapping.dao;

import com.example.jpa_exercise_relational_mapping.model.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AppUserDAOImpl implements AppUserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public AppUser save(AppUser appUser) {
        if (appUser == null) throw  new IllegalArgumentException("appUser was null");
        entityManager.persist(appUser);
        return appUser;
    }

    @Transactional(readOnly = true)
    @Override
    public AppUser findById(int id) {
        if (id <= 0) throw  new IllegalArgumentException("Invalid id");
        AppUser appUser = entityManager.find(AppUser.class, id);
        return appUser;
    }

    @Transactional
    @Override
    public void delete(AppUser appUser) {
//        findById(appUser.getUserId());
        entityManager.remove(appUser);
    }
}
