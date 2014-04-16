package org.codemomentum.spring.bootstrap;

import org.codemomentum.spring.bootstrap.messaging.IReceiver;
import org.codemomentum.spring.bootstrap.messaging.ISender;
import org.codemomentum.spring.bootstrap.storage.entity.Car;
import org.codemomentum.spring.bootstrap.storage.repo.ICarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * @author Halit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath:spring/applicationContext.xml"})
public class TransactionalTest {

    @Autowired
    ISender sender;

    @Autowired
    IReceiver receiver;

    @Autowired
    ICarRepository carRepository;

    @Test
    public void testBasicFlow() throws Exception {

        //no messages should be in the db

        Car bmw = new Car();
        bmw.setColor("blue");
        bmw.setModel("bmw");
        carRepository.saveAndSendMessage(bmw);
        Thread.sleep(2000);

        String received = receiver.receiveMail();

        assertNotNull("a message should have been received", received);


        Car another = new Car();
        bmw.setColor("black");
        bmw.setModel("bmw");
        try {
            carRepository.saveAndSendMessage(another);
        } catch (Exception e) {
            //wont save due to uniqueness
            //ignored
        }
        Thread.sleep(2000);

        try {
            received = receiver.receiveMail();
            fail("should not have consumed another message!");
        } catch (NullPointerException npe) {
            //ignore
        }


    }

}
