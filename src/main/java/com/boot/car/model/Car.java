package com.boot.car.model;

import lombok.Data;

@Data
public class Car {
    private String carId;

    private String carName;

    private String carModel;

    private String carType;

    private double carPrice;

    private int userRating;
    private String carColor;

}
