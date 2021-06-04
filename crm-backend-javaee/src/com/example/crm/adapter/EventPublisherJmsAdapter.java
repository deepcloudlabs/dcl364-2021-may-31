package com.example.crm.adapter;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.json.Json;

import com.example.crm.application.business.event.CustomerEvent;
import com.example.crm.infra.EventPublisher;

// wildfly: standalone/configuration/standalone-full.xml:
// <jms-queue name="crmQueue" entries="java:/jms/queue/crmQueue"/>
@Stateless
@Default
public class EventPublisherJmsAdapter implements EventPublisher<CustomerEvent> {
	@Inject
	private JMSContext jmsContext;
	
	@Resource(mappedName = "java:/jms/queue/crmQueue") // JNDI Name
	private Queue queue;
	
	@Override
	public void publish(CustomerEvent event) {
		   jmsContext.createProducer().send(queue, toJson(event));
	}

	private String toJson(CustomerEvent event) {
			return Json.createObjectBuilder()
					   .add("eventType", event.getClass().getSimpleName())
				       .add("identity", event.getKimlik().getValue())
				       .build()
				       .toString();
	}

}
