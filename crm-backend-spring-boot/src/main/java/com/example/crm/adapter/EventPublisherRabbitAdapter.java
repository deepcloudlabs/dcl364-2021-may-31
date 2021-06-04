package com.example.crm.adapter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.crm.application.business.event.CustomerEvent;
import com.example.crm.infra.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// wildfly: standalone/configuration/standalone-full.xml:
// <jms-queue name="crmQueue" entries="java:/jms/queue/crmQueue"/>
@Service
@ConditionalOnProperty(name = "messaging.bus", havingValue = "rabbit")
public class EventPublisherRabbitAdapter implements EventPublisher<CustomerEvent> {
	@Autowired
	private RabbitTemplate rabbitTemplate; // GoF's Template Pattern
	@Autowired
	private ObjectMapper mapper;

	@Override
	public void publish(CustomerEvent event) {
		   try {
			rabbitTemplate.convertAndSend("crm-exchange", null,mapper.writeValueAsString(event));
		} catch (JsonProcessingException e) {
			System.err.println("Error in converting object to json: "+e.getMessage());
		}
	}

}
