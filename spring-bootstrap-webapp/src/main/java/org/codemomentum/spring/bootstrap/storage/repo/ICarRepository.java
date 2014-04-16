package org.codemomentum.spring.bootstrap.storage.repo;

import org.codemomentum.spring.bootstrap.storage.entity.Car;

/**
 * @author Halit
 */
public interface ICarRepository {
    void save(Car car);

    void saveAndSendMessage(Car car) throws Exception;

    void update(Car car);

    void delete(Car car);

    Car findByModel(String model);
}
