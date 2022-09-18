package com.example.jpa_exercise_relational_mapping.repository;

import com.example.jpa_exercise_relational_mapping.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressRepositoryTest {

    public List<Address> addresses(){
        return Arrays.asList(
                new Address("Minervävagen20","38951", "Karlskrona"),
                new Address("Leckeby","25843", "Karlskrona"),
                new Address("Silosvägen","25843", "Karlshamn"),
                new Address("Storgatan","25843", "Växjö")
        );
    }

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    AddressRepository testRepo;
    Address testAddress;

    @BeforeEach
    void setUp() {
        List<Address> persistedAddresses = addresses().stream()
                .map(entityManager:: persist)
                .collect(Collectors.toList());

        testAddress = persistedAddresses.get(0);
    }

    @Test
    void findAllAddressBySpecifiedCity() {
        List<Address> foundCities = testRepo.findAllAddressBySpecifiedCity("karlskrona");
        assertNotNull(foundCities);
        assertEquals(2,foundCities.size());

    }
}