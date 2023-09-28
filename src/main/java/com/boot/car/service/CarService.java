package com.boot.car.service;

import com.boot.car.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CarService {

    public Car addCar(Car car);

    public List<Car> viewAllCar();

    public List<Car> getCarByModel(String carModel);

    public List<Car> getCarByRating(int rating) throws InterruptedException;

    public List<Map<String, Object>> viewCarByCarId(String carid);

    public List<Car> viewCarByPrice(double carPrice);

    public List<Car> countCarName(String carName);

    String deleteCarById(String carId);

    List<Car> findCarByColor(String carColor) throws InterruptedException;

}
