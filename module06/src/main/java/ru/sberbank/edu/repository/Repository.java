package ru.sberbank.edu.repository;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface Repository<T, I> {

    /**
     * Creates a new T record if the T with the specified ID does not exist, or updates the existing record if it does.
     *
     * @param t The T to create or update
     * @return The T that was created or updated
     * @throws SQLException If there is an error interacting with the database
     */
    T createOrUpdate(T t) throws SQLException;

    /**
     * Creates a new T record for each T in the specified collection.
     *
     * @param tCollection The collection of T to create
     * @return A set of the T that were created
     * @throws RuntimeException If there is an error interacting with the database
     */
    Set<T> createAll(Collection<T> tCollection);

    /**
     * Retrieves all T records from the database.
     *
     * @return A set of the retrieved T records
     * @throws RuntimeException If there is an error interacting with the database
     */
    Set<T> findAll();

    /**
     * Retrieves the T record with the specified ID from the database.
     *
     * @param id The ID of the T to retrieve
     * @return An optional containing the retrieved T, or empty if no T with the specified ID was found
     * @throws SQLException If there is an error interacting with the database
     */
    Optional<T> findById(I id) throws SQLException;

    /**
     * Deletes the T record with the specified ID from the database.
     *
     * @param id The ID of the T to delete
     * @return `true` if the T was successfully deleted, `false` otherwise
     * @throws RuntimeException If there is an error interacting with the database
     */
    Boolean deleteById(I id);

    /**
     * Deletes all T records from the database.
     *
     * @return `true` if at least one T record was deleted, `false` otherwise
     * @throws RuntimeException If there is an error interacting with the database
     */
    Boolean deleteAll();
}
