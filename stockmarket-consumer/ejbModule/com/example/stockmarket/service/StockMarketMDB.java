package com.example.stockmarket.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
	activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destination", 
				propertyValue="java:/jms/queue/stockQueue"
		),
		@ActivationConfigProperty(
				propertyName = "destinationType", 
				propertyValue="javax.jms.Queue" // or javax.jms.Topic 
		)			
	}
)
public class StockMarketMDB implements MessageListener {

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				var json = ((TextMessage)message).getText();
				System.err.println("MDB has received a message: "+json);
			} catch (JMSException e) {
				System.err.println("Error in receiving a message: "+e.getMessage());
			}
		}
		
	}

}
