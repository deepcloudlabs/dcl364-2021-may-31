package com.example.stockmarket.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.json.Json;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.example.stockmarket.event.StockPriceChangedEvent;

@Singleton
@ServerEndpoint("/changes")
public class StockmarketWebsocketService {

	private Map<String,Session> sessions = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpenSession(Session session) {
		sessions.put(session.getId(), session);
	}
	
	@OnClose
	public void onClosesSession(Session session) {
		sessions.remove(session.getId());
	}
	
	public void listenEvent(@Observes StockPriceChangedEvent event) {
		var json = Json.createObjectBuilder()
				       .add("symbol", event.getSymbol())
				       .add("old_price", event.getOldPrice())
				       .add("new_price", event.getNewPrice())
				       .build()
				       .toString();
		System.err.println("Event received: "+event);
		sessions.forEach((sessionId,session) -> {
			try {
				session.getBasicRemote().sendText(json);
			} catch (IOException e) {
				System.err.println("error in sending event through websocket: "+e.getMessage());
			}
		});
	}
}
