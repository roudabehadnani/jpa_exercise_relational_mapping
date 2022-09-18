package com.example.jpa_exercise_relational_mapping.repository;

import com.example.jpa_exercise_relational_mapping.model.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Integer> {

    //Find all addresses in a specified city

    @Query("SELECT a FROM Address a WHERE lower(a.city) = lower(:city)")
    List<Address> findAllAddressBySpecifiedCity(@Param("city") String city);


}
