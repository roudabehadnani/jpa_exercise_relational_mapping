package com.example.jpa_exercise_relational_mapping.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;

    @Column(length = 150, nullable = false)
    private String statusCode;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,CascadeType.REFRESH})
    private Collection<Car> cars;

    public Status() {
    }

    public Status(String statusCode) {
        this.statusCode = statusCode;
        setCars(new ArrayList<>());
    }

    public void addCar(Car car){
        if (car ==  null){
            throw  new IllegalArgumentException("Invalid parameter: Car was null");
        }
//        if (cars == null){
//            cars = new ArrayList<>();
//        }
        cars.add(car);
        car.getStatusCodes().add(this);
    }

    public void removeCar(Car car){
        if (car == null){
            throw new IllegalArgumentException("Invalid parameter: Car was null");
        }
        car.getStatusCodes().remove(this);
        cars.remove(car);
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return statusId == status.statusId && Objects.equals(statusCode, status.statusCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, statusCode);
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", statusCode='" + statusCode + '\'' +
                ", cars=" + cars +
                '}';
    }
}
