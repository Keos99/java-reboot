package ru.sberbank.edu.service;

import java.sql.SQLException;

public interface CarService {

    /**
     * Adds a new car to the database.
     *
     * @param id The ID of the car to add
     * @param model The model of the car to add
     * @throws SQLException If there is an error interacting with the database
     */
    void addCar(String id, String model) throws SQLException;

    /**
     * Edits the model of an existing car.
     *
     * @param id The ID of the car to edit
     * @param model The new model of the car
     * @throws SQLException If there is an error interacting with the database
     */
    void editModel(String id, String model) throws SQLException;

    /**
     * Deletes an existing car from the database.
     *
     * @param id The ID of the car to delete
     */
    void deleteCar(String id);

}
