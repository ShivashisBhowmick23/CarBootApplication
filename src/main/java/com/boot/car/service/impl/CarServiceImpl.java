package com.boot.car.service.impl;

import com.boot.car.model.Car;
import com.boot.car.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarServiceImpl implements CarService {


    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    JdbcTemplate jdbcTemp;

    public CarServiceImpl(JdbcTemplate jdbcTemp) {
        super();
        this.jdbcTemp = jdbcTemp;
    }

    @Override
    public Car addCar(Car car) {

        if (car.getCarId() != null) {
            jdbcTemp.update("INSERT INTO cartable(carId,carName,carModel,carType,carPrice,userRating,carColor) VALUES(?, ?, ?, ?, ? ,?,?)", car.getCarId(), car.getCarName(), car.getCarModel(), car.getCarType(), car.getCarPrice(), car.getUserRating(),car.getCarColor());

            logger.debug("car added successfully");
        } else logger.debug("null value exists");
        return car;
    }

    @Override
    public List<Car> viewAllCar() {
        List<Car> list = jdbcTemp.query("SELECT * FROM cartable", new BeanPropertyRowMapper<Car>(Car.class));
        long total = list.stream().count();
        logger.debug("Total number of cars" + total);
        return list;
    }

    @Override
    public List<Car> getCarByModel(String carModel) {
        List<Car> carModelList = jdbcTemp.query("SELECT * FROM cartable  WHERE carModel = ?", new BeanPropertyRowMapper<Car>(Car.class), carModel);
        long totalCarModelList = carModelList.stream().count();
        logger.debug("Number of Cars found with the model name: " + totalCarModelList + " CarModel is: " + carModel);
        return carModelList;
    }

    @Override
    public List<Car> getCarByRating(int rating) throws InterruptedException {
        List<Car> carRate = jdbcTemp.query("SELECT * FROM cartable WHERE userRating = ?", new BeanPropertyRowMapper<Car>(Car.class), rating);
        long numberOfCarsUserRating = carRate.stream().count();
        Thread.sleep(2000);
        logger.debug("Number of cars: " + numberOfCarsUserRating + " with rating " + rating);
        return carRate;

    }

    @Override
    public List<Map<String, Object>> viewCarByCarId(String carId) {

//		return jdbcTemp.queryForList("SELECT * FROM cartable  WHERE carId = ?", new BeanPropertyRowMapper<Car>(Car.class),carId);

        String sql = "SELECT * FROM cartable  WHERE carId = :carId";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("carId", carId);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemp.getDataSource());

        return namedParameterJdbcTemplate.queryForList(sql, parameters);
    }

    @Override
    public List<Car> viewCarByPrice(double carPrice) {
        if (carPrice >= 2000000) {
            List<Car> carPriceList = jdbcTemp.query("SELECT * FROM cartable WHERE carPrice >= ?", new BeanPropertyRowMapper<Car>(Car.class), carPrice);
            long countMoreThan2000000 = carPriceList.stream().count();

            logger.debug("Car Found of Price Greater and equal to 2000000: " + carPrice);
            logger.debug("Number of Cars of Price greater than equal to 2000000: " + countMoreThan2000000);
            return carPriceList;
        } else if (carPrice <= 1000000) {
            List<Car> carPriceList = jdbcTemp.query("SELECT * FROM cartable  WHERE carPrice <= ?", new BeanPropertyRowMapper<Car>(Car.class), carPrice);
            long countLessThan1000000 = carPriceList.stream().count();

            logger.debug("Car Found of Price less than 1000000: " + carPrice);
            logger.debug("Number of Cars of Price less than equal to 1000000: " + countLessThan1000000);
            return carPriceList;


        }


        return null;
    }


    @Override
    public List<Car> countCarName(String carName) {

        List<Car> CarCountList = jdbcTemp.query("SELECT * FROM cartable WHERE carName = ?", new BeanPropertyRowMapper<Car>(Car.class), carName);
        long carCount = CarCountList.stream().count();
        logger.debug("Number of " + carName + " in the showroom: " + carCount);
        return CarCountList;


    }

    @Override
    public String deleteCarById(String carId) {
        logger.debug("deleteCarById method executed");
        jdbcTemp.update("DELETE FROM cartable WHERE carId=?", carId);
        return "Car Deleted from the showroom: " + carId;
    }

    @Override
    public List<Car> findCarByColor(String carColor) throws InterruptedException {
        List<Car> carColorList = jdbcTemp.query("SELECT * FROM cartable WHERE carColor = ?", new BeanPropertyRowMapper<Car>(Car.class), carColor);
        logger.debug("Finding cars....");
        Thread.sleep(3000);
        logger.debug("Found some cars of color:: "+carColor);
        return carColorList;
    }
}

