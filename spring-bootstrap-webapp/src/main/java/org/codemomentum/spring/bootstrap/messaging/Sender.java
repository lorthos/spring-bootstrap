package org.codemomentum.spring.bootstrap.messaging;

import org.springframework.jms.core.support.JmsGatewaySupport;

import java.util.HashMap;
import java.util.Map;

public class Sender extends JmsGatewaySupport{

    public void send(final String messageToBeSent) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("content", messageToBeSent);
        getJmsTemplate().convertAndSend(map);
    }
}
