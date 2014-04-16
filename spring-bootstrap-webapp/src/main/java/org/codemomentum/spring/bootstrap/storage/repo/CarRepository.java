package org.codemomentum.spring.bootstrap.storage.repo;

import org.codemomentum.spring.bootstrap.storage.entity.Car;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * @author Halit
 */


public class CarRepository extends HibernateDaoSupport {
    public void save(Car car) {
        getHibernateTemplate().save(car);
    }

    public void update(Car car) {
        getHibernateTemplate().update(car);
    }

    public void delete(Car car) {
        getHibernateTemplate().delete(car);
    }

    public Car findByModel(String model) {
        List list = getHibernateTemplate().find(
                "from Car where model=?", model
        );
        return (Car) list.get(0);
    }
}
