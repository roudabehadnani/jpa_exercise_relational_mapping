package com.example.jpa_exercise_relational_mapping.repository;

import com.example.jpa_exercise_relational_mapping.model.Address;
import com.example.jpa_exercise_relational_mapping.model.AppUser;
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
class AppUserRepositoryTest {

    public List<AppUser> appUsers(){
        return Arrays.asList(
                new AppUser("rod@gmail.com", "Roudabe Ad", "123ra"),
                new AppUser("soheil@gmail.com", "Soheil kei", "lok987"),
                new AppUser("johan@gmail.com", "Johan Anders", "lkrv98")
        );
    }

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    AppUserRepository testRepo ;
    AppUser testAppUser;

    @BeforeEach
    void setUp() {
            List<AppUser> persistedAppUser = appUsers().stream()
                    .map(entityManager:: persist)
                    .collect(Collectors.toList());

            testAppUser = persistedAppUser.get(0);
        }

    @Test
    void findByEmailANDPass() {
        String pass = "123ra";
        String mail = "rod@gmail.com";
        AppUser foundEmailPass = testRepo.findByEmailANDPass(mail, pass);
        assertNotNull(foundEmailPass);
        assertEquals("Roudabe Ad", foundEmailPass.getName());


    }

    @Test
    void findByName() {
        String stringName = "oh";
        List<AppUser> found = testRepo.findByName(stringName);
        assertEquals(2,found.size());
    }
}