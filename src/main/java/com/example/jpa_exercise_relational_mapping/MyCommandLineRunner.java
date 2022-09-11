package com.example.jpa_exercise_relational_mapping;

import com.example.jpa_exercise_relational_mapping.dao.AppUserDAO;
import com.example.jpa_exercise_relational_mapping.model.Address;
import com.example.jpa_exercise_relational_mapping.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    public MyCommandLineRunner(AppUserDAO appUserDAO, EntityManager entityManager) {
        this.appUserDAO = appUserDAO;
        this.entityManager = entityManager;
    }

    private final AppUserDAO appUserDAO;
    private final EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {

        AppUser roudabeh = new AppUser("rod@gmail.com", "Roudabe Ad", "123ra");
        AppUser soheil = new AppUser("soheil@gmail.com", "Soheil kei", "lok987");

        roudabeh = appUserDAO.save(roudabeh);
        soheil = appUserDAO.save(soheil);

        Address address = new Address("Minervävagen20","38951", "Karlskrona");
        Address address1 = new Address("Leckeby","25843", "Karlskrona");

        roudabeh.setAddress(address);
        soheil.setAddress(address1);

        entityManager.flush();

        System.out.println("-------Print---------");
        System.out.println(roudabeh);
        System.out.println(soheil);






    }
}
