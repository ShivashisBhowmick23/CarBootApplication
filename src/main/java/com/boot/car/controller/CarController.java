package com.boot.car.controller;

import com.boot.car.model.Car;
import com.boot.car.service.impl.CarServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/car/myshowroom")
public class CarController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);
    @Autowired
    CarServiceImpl carServiceImpl;

    @PostMapping("/addCar")
    public Car addCar(@RequestBody Car car) {

  logger.info("Car added: " + car.getCarName());
        return carServiceImpl.addCar(car);

    }

    @GetMapping("/allCars")
    public List<Car> getAllCar() throws InterruptedException {

        logger.debug("fetching the  result");

        Thread.sleep(2000);

        logger.info("Collecting cars from the store..");
        logger.info("All Cars" + carServiceImpl.viewAllCar());
        return carServiceImpl.viewAllCar();

    }

    @GetMapping("/getByModel/{carModel}")
    public List<Car> getCarbyModel(@PathVariable("carModel") String carModel) {
        logger.info("Car Found");
        return carServiceImpl.getCarByModel(carModel);
    }

    @GetMapping("/getCarbyUserRating/{userRating}")
    public List<Car> getCarbyUserRating(@PathVariable("userRating") int userRating) throws InterruptedException {
        logger.info("Car Found with user rating");
        return carServiceImpl.getCarByRating(userRating);
    }

    @GetMapping("/getCarByCarId/{carId}")
    public List<Map<String, Object>> getCarByCarId(@PathVariable String carId) {
        List<Map<String, Object>> car = null;
        if (!carId.isEmpty()) {
            logger.info("Car found with the carID: " + carId);
            car = carServiceImpl.viewCarByCarId(carId);

        }
        return car;
    }

    @GetMapping("/getCarByCarPrice/{carPrice}")
    public List<Car> getCarByCarPrice(@PathVariable double carPrice) {
        logger.info("Fetching cars based on price: " + carPrice);
        return carServiceImpl.viewCarByPrice(carPrice);
    }

    @GetMapping("/getCarByName/{carName}")
    public List<Car> countAudi(@PathVariable String carName) {
        logger.info("count: " + carName);
        return carServiceImpl.countCarName(carName);

    }

    @DeleteMapping("/deleteCar/{carId}")
    public String deleteCarById(@PathVariable String carId) {
        logger.info("car deleted from the database with carID: " + carId);
        return carServiceImpl.deleteCarById(carId);
    }
@GetMapping("/findCar/carColor/{carColor}")
    public List<Car> findCarsByColor(@PathVariable String carColor) throws InterruptedException {
        logger.info("Finding from Database...");
        Thread.sleep(1000);
        logger.info("Please wait...");
        return carServiceImpl.findCarByColor(carColor);
    }
}



