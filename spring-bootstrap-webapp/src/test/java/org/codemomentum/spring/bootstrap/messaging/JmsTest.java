package org.codemomentum.spring.bootstrap.messaging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Halit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath:spring/applicationContext.xml"})
public class JmsTest {

    @Autowired
    Sender sender;

    @Autowired
    Receiver receiver;

    @Test
    public void testBasicFlow() throws Exception {
        sender.send("foo bar");

        Thread.sleep(5000);

        String received = receiver.receiveMail();

        assertEquals("messages does not match", "foo bar", received);

        Thread.sleep(100000);
    }
}
