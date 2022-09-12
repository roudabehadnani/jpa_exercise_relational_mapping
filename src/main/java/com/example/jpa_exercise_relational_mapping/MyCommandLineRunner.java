package com.example.jpa_exercise_relational_mapping;

import com.example.jpa_exercise_relational_mapping.dao.AppUserDAO;
import com.example.jpa_exercise_relational_mapping.dao.CarDAO;
import com.example.jpa_exercise_relational_mapping.model.Address;
import com.example.jpa_exercise_relational_mapping.model.AppUser;
import com.example.jpa_exercise_relational_mapping.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    public MyCommandLineRunner(AppUserDAO appUserDAO, CarDAO carDAO, EntityManager entityManager) {
        this.appUserDAO = appUserDAO;
        this.carDAO = carDAO;
        this.entityManager = entityManager;
    }

    private final AppUserDAO appUserDAO;
    private final CarDAO carDAO;
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








    }
}
