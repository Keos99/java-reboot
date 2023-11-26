package ru.sberbank.edu.service;


import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarRepository;

import java.sql.SQLException;
import java.util.Optional;

public class CarServiceImpl implements CarService {
    /**
     * The car repository used to interact with the database.
     */
    private CarRepository carRepository;

    /**
     * Constructs a new `CarServiceImpl` instance.
     *
     * @param carRepository The car repository used to interact with the database
     */
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Adds a new car to the database.
     *
     * @param id The ID of the car to add
     * @param model The model of the car to add
     * @throws SQLException If there is an error interacting with the database
     */
    @Override
    public void addCar(String id, String model) throws SQLException {
        carRepository.createOrUpdate(new Car(id, model));
    }

    /**
     * Edits the model of an existing car.
     *
     * @param id The ID of the car to edit
     * @param newModel The new model of the car
     * @throws SQLException If there is an error interacting with the database
     */
    @Override
    public void editModel(String id, String newModel) throws SQLException {
        Optional<Car> optCar = carRepository.findById(id);
        Car car = optCar.orElseThrow();
        updateCarModel(car, newModel);
    }

    /**
     * Deletes an existing car from the database.
     *
     * @param id The ID of the car to delete
     */
    @Override
    public void deleteCar(String id) {
        carRepository.deleteById(id);
    }

    /**
     * Updates the model of the specified car.
     *
     * @param car The car to update
     * @param newModel The new model of the car
     * @throws SQLException If there is an error interacting with the database
     */
    private void updateCarModel(Car car, String newModel) {
        car.setModel(newModel);
        try {
            carRepository.createOrUpdate(car);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
