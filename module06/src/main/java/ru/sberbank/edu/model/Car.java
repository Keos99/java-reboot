package ru.sberbank.edu.model;

import java.util.Objects;

/**
 * Represents a car with an ID, model, and setter/getter methods for accessing and modifying its properties.
 */
public class Car {
    /**
     * The unique identifier of the car.
     */
    private final String id;
    /**
     * The model of the car.
     */
    private String model;

    /**
     * Constructs a new Car object with the specified ID and model.
     * @param id the unique identifier of the car
     * @param model the model of the car
     */
    public Car(String id, String model) {
        this.id = id;
        this.model = model;
    }

    /**
     * Gets the ID of the car.
     * @return the ID of the car
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the model of the car.
     * @return the model of the car
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the car.
     * @param model the model of the car
     */
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(getId(), car.getId()) && Objects.equals(getModel(), car.getModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
