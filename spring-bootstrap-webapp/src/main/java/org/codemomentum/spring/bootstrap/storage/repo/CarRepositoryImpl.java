package org.codemomentum.spring.bootstrap.storage.repo;

import org.codemomentum.spring.bootstrap.storage.entity.Car;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Halit
 */


public class CarRepositoryImpl extends HibernateDaoSupport implements ICarRepository{

    @Override
    public void save(Car car) {
        getHibernateTemplate().save(car);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void saveAndSendMessage(Car car) throws Exception{
        getHibernateTemplate().save(car);
    }

    @Override
    public void update(Car car) {
        getHibernateTemplate().update(car);
    }

    @Override
    public void delete(Car car) {
        getHibernateTemplate().delete(car);
    }

    @Override
    public Car findByModel(String model) {
        List list = getHibernateTemplate().find(
                "from Car where model=?", model
        );
        return (Car) list.get(0);
    }
}
