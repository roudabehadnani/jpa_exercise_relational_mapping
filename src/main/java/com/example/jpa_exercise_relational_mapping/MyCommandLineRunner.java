package com.example.jpa_exercise_relational_mapping;

import com.example.jpa_exercise_relational_mapping.dao.AppUserDAO;
import com.example.jpa_exercise_relational_mapping.dao.CarDAO;
import com.example.jpa_exercise_relational_mapping.dao.StatusDAO;
import com.example.jpa_exercise_relational_mapping.model.Address;
import com.example.jpa_exercise_relational_mapping.model.AppUser;
import com.example.jpa_exercise_relational_mapping.model.Car;
import com.example.jpa_exercise_relational_mapping.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.SecondaryTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Transactional
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    public MyCommandLineRunner(AppUserDAO appUserDAO, CarDAO carDAO, StatusDAO statusDAO, EntityManager entityManager) {
        this.appUserDAO = appUserDAO;
        this.carDAO = carDAO;
        this.statusDAO = statusDAO;
        this.entityManager = entityManager;
    }

    private final AppUserDAO appUserDAO;
    private final CarDAO carDAO;
    private final StatusDAO statusDAO;
    private final EntityManager entityManager;

    @Override
    public void run(String... args){

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

        System.out.println("-------Save Cars---------");
        Car volvo = carDAO.save( new Car("AFR 124", "Volvo"));
        Car bmw = carDAO.save(new Car("NMK 321", "BMW"));
        Car skoda = carDAO.save(new Car("PLO987", "Skoda"));

        roudabeh.addCar(volvo);
        roudabeh.addCar(skoda);
        entityManager.flush();
        roudabeh.getOwnedCars().forEach(System.out::println);

        soheil.addCar(bmw);
        entityManager.flush();
        soheil.getOwnedCars().forEach(System.out::println);


        System.out.println("-------Save Status---------");
        Status status1 = statusDAO.save(new Status("Out of Service"));
        Status status2 = statusDAO.save(new Status("Clear and Available"));
        Status status3 = statusDAO.save(new Status("At Transporting Destination"));


        volvo.addStatus(status1);
        bmw.addStatus(status1);
        skoda.addStatus(status2);
        bmw.addStatus(status3);
































    }
}
