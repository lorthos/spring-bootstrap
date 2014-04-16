package org.codemomentum.spring.bootstrap;

import org.codemomentum.spring.bootstrap.messaging.Receiver;
import org.codemomentum.spring.bootstrap.messaging.Sender;
import org.codemomentum.spring.bootstrap.storage.repo.CarRepository;
import org.codemomentum.spring.bootstrap.storage.entity.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Halit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath:spring/applicationContext.xml"})
public class TransactionalTest {

    @Autowired
    Sender sender;

    @Autowired
    Receiver receiver;

    @Autowired
    CarRepository carRepository;

    @Test
    public void testBasicFlow() throws Exception {

        Car bmw = new Car();
        bmw.setColor("blue");
        bmw.setModel("bmw");
        carRepository.save(bmw);
    }

}
