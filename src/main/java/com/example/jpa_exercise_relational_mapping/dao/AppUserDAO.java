package com.example.jpa_exercise_relational_mapping.dao;

import com.example.jpa_exercise_relational_mapping.model.AppUser;

public interface AppUserDAO {

    AppUser findById (int id);
    AppUser save (AppUser appUser);
    void delete (AppUser appUser);
}
