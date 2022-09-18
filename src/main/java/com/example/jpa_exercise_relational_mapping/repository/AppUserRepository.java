package com.example.jpa_exercise_relational_mapping.repository;

import com.example.jpa_exercise_relational_mapping.model.Address;
import com.example.jpa_exercise_relational_mapping.model.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    //a. Find an AppUser with matching email.
    AppUser findByEmailIgnoreCase(String email);

    //b. Find an AppUser with matching email and password.
    @Query("SELECT a FROM AppUser a WHERE a.email = :email AND a.password = :pass")
    AppUser findByEmailANDPass(@Param("email") String email, @Param("pass") String password);

    //c. Find AppUser’s by name containing specified String.
    @Query("SELECT a FROM AppUser a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name , '%'))")
    List<AppUser> findByName(@Param("name") String name);

    //d. Find AppUser’s that live on the same address.
    List<AppUser> findAppUsersByAddress(Address address);


    //e. Find AppUser’s that live in the same city.
    List<AppUser> findAppUsersByAddressCityIsContainingIgnoreCase(String city);
}
