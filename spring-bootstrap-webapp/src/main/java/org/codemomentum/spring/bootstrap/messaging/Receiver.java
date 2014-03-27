package org.codemomentum.spring.bootstrap.messaging;

import org.springframework.jms.core.support.JmsGatewaySupport;

import java.util.Map;

public class Receiver extends JmsGatewaySupport {

    public String receiveMail() {
        Map map = (Map) getJmsTemplate().receiveAndConvert();
        return map.get("content").toString();
    }
}