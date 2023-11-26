package ru.sberbank.edu.repository;

import ru.sberbank.edu.model.Car;

import java.util.Set;

public interface CarRepository extends Repository<Car, String> {

    /**
     * Retrieves all car records from the database that have the specified model.
     *
     * @param model The model of the cars to retrieve
     * @return A set of the retrieved car records
     * @throws RuntimeException If there is an error interacting with the database
     */
    Set<Car> findByModel(String model);
}
