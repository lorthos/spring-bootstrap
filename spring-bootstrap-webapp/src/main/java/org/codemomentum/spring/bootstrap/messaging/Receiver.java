package org.codemomentum.spring.bootstrap.messaging;

import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public class Receiver extends JmsGatewaySupport implements IReceiver{

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String receiveMail() {
        Map map = (Map) getJmsTemplate().receiveAndConvert();
        return map.get("content").toString();
    }
}