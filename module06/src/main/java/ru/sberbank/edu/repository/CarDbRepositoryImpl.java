package ru.sberbank.edu.repository;


import ru.sberbank.edu.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CarDbRepositoryImpl implements CarRepository {
    private final Connection connection;
    private static final String CREATE_CAR_SQL = "INSERT INTO car (id, model) VALUES (?,?)";
    private static final String UPDATE_CAR_SQL = "UPDATE car SET model = ? WHERE id = ?";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";
    private static final String FIND_ALL_CARS = "SELECT * FROM car";
    private static final String FIND_BY_MODEL = "SELECT * FROM car WHERE model = ?";
    private static final String DELETE_BY_ID = "DELETE FROM car WHERE id = ?";
    private static final String DELETE_ALL = "DELETE FROM car";

    private final PreparedStatement createPreStmt;
    private final PreparedStatement updatePreStmt;
    private final PreparedStatement findByIdPreStmt;
    private final PreparedStatement findAllPreStmt;
    private final PreparedStatement findByModelPreStmt;
    private final PreparedStatement deleteById;
    private final PreparedStatement deleteAll;

    public CarDbRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.createPreStmt = connection.prepareStatement(CREATE_CAR_SQL);
        this.updatePreStmt = connection.prepareStatement(UPDATE_CAR_SQL);
        this.findByIdPreStmt = connection.prepareStatement(SELECT_CAR_BY_ID);
        this.findAllPreStmt = connection.prepareStatement(FIND_ALL_CARS);
        this.findByModelPreStmt = connection.prepareStatement(FIND_BY_MODEL);
        this.deleteById = connection.prepareStatement(DELETE_BY_ID);
        this.deleteAll = connection.prepareStatement(DELETE_ALL);
    }

    /**
     * Creates a new car record if the car with the specified ID does not exist, or updates the existing record if it does.
     *
     * @param car The car to create or update
     * @return The car that was created or updated
     * @throws SQLException If there is an error interacting with the database
     */
    @Override
    public Car createOrUpdate(Car car) throws SQLException {
        Optional<Car> optCar = findById(car.getId());
        if (optCar.isEmpty()) {
            createPreStmt.setString(1, car.getId());
            createPreStmt.setString(2, car.getModel());
            createPreStmt.executeUpdate();
        } else {
            updatePreStmt.setString(1, car.getModel());
            updatePreStmt.setString(2, car.getId());
            updatePreStmt.executeUpdate();
        }
        return car;
    }

    /**
     * Creates a new car record for each car in the specified collection.
     *
     * @param cars The collection of cars to create
     * @return A set of the cars that were created
     * @throws RuntimeException If there is an error interacting with the database
     */
    @Override
    public Set<Car> createAll(Collection<Car> cars) {
        Set<Car> createdCars = new HashSet<>();
        for (Car car : cars) {
            try {
                createdCars.add(createOrUpdate(car));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return createdCars;
    }

    /**
     * Retrieves all car records from the database.
     *
     * @return A set of the retrieved car records
     * @throws RuntimeException If there is an error interacting with the database
     */
    @Override
    public Set<Car> findAll() {
        Set<Car> cars = new HashSet<>();
        ResultSet resultSet = null;
        try {
            resultSet = findAllPreStmt.executeQuery();
            while (resultSet.next()) {
                cars.add(new Car(resultSet.getString(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    /**
     * Retrieves the car record with the specified ID from the database.
     *
     * @param id The ID of the car to retrieve
     * @return An optional containing the retrieved car, or empty if no car with the specified ID was found
     * @throws SQLException If there is an error interacting with the database
     */
    @Override
    public Optional<Car> findById(String id) throws SQLException {
        // validation
        int rowsCount = countRowsById(id);
        if (rowsCount > 1) {
            throw new RuntimeException("Car with id = " + id + " was found many times (" + rowsCount + ").");
        } else if (rowsCount == 0) {
            return Optional.empty();
        }

        findByIdPreStmt.setString(1, id);
        ResultSet resultSet = findByIdPreStmt.executeQuery();

        resultSet.next();
        Car car = new Car(resultSet.getString(1), resultSet.getString(2));
        return Optional.of(car);
    }

    /**
     * Deletes the car record with the specified ID from the database.
     *
     * @param id The ID of the car to delete
     * @return `true` if the car was successfully deleted, `false` otherwise
     * @throws RuntimeException If there is an error interacting with the database
     */
    @Override
    public Boolean deleteById(String id) {
        int rowsAffected = 0;
        try {
            deleteById.setString(1, id);
            rowsAffected = deleteById.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected > 0;
    }

    /**
     * Deletes all car records from the database.
     *
     * @return `true` if at least one car record was deleted, `false` otherwise
     * @throws RuntimeException If there is an error interacting with the database
     */
    @Override
    public Boolean deleteAll() {
        int rowsAffected = 0;
        try {
            rowsAffected = deleteAll.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected > 0;
    }

    /**
     * Counts the number of car records with the specified ID in the database.
     *
     * @param id The ID of the car to count
     * @return The number of car records with the specified ID
     * @throws SQLException If there is an error interacting with the database
     */
    private int countRowsById(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM car where id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int rowCount = 0;
        while (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }

    /**
     * Retrieves all car records from the database that have the specified model.
     *
     * @param model The model of the cars to retrieve
     * @return A set of the retrieved car records
     * @throws RuntimeException If there is an error interacting with the database
     */
    @Override
    public Set<Car> findByModel(String model) {
        Set<Car> cars = new HashSet<>();
        ResultSet resultSet;
        try {
            findByModelPreStmt.setString(1, model);
            resultSet = findByModelPreStmt.executeQuery();
            while (resultSet.next()) {
                cars.add(new Car(resultSet.getString(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }
}
