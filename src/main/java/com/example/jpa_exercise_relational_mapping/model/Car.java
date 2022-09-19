package com.example.jpa_exercise_relational_mapping.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int carId;

    @Column(length = 100, nullable = false)
    private String regNumber;

    @Column(length = 100, nullable = false)
    private String brand;

    @Column
    private LocalDate regDate;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private AppUser owner;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},
            mappedBy = "cars")
    private Collection<Status> statusCodes;

    public Car() {
    }

    public Car(String regNumber, String brand, LocalDate regDate) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.regDate = regDate;
        setStatusCodes(new ArrayList<>());
    }

    public Car(String regNumber, String brand) {
        this.regNumber = regNumber;
        this.brand = brand;
    }

    public void addStatus(Status status){
        if (status == null){
            throw new IllegalArgumentException("Invalid parameter: Status was null");
        }
        if (statusCodes == null){
            setStatusCodes(new ArrayList<>());
        }
        if (!statusCodes.contains(status)){
            status.getCars().add(this);
            statusCodes.add(status);
        }
    }

    public void removeStatus(Status status){
        if (status == null){
            throw new IllegalArgumentException("Invalid parameter: Status was null");
        }
        status.getCars().remove(this);
        statusCodes.remove(status);
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public Collection<Status> getStatusCodes() {
        return statusCodes;
    }

    public void setStatusCodes(Collection<Status> statusCodes) {
        this.statusCodes = statusCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId == car.carId && Objects.equals(regNumber, car.regNumber) && Objects.equals(brand, car.brand) && Objects.equals(regDate, car.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, regNumber, brand, regDate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", regNumber='" + regNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", regDate=" + regDate +
                ", owner=" + owner +
                ", statusCodes=" + statusCodes +
                '}';
    }
}
