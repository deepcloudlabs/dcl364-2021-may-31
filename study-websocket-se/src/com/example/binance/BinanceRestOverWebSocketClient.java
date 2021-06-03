package com.example.binance;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class BinanceRestOverWebSocketClient {

	private static final String binanceWsUrl= "wss://stream.binance.com:9443/ws/btcusdt@trade";

	public static void main(String[] args) throws InterruptedException {
		var listener = new BtcWebSocketListener();
		HttpClient.newHttpClient().newWebSocketBuilder().buildAsync(URI.create(binanceWsUrl), listener);
		System.err.println("Client is running...");
		TimeUnit.MINUTES.sleep(1);
	}

}

class BtcWebSocketListener implements Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Connected to the binance server!");
		webSocket.request(1);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.err.println(data);
		webSocket.request(1);
		return null;
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Disconnected from the binance server!");
		return Listener.super.onClose(webSocket, statusCode, reason);
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		System.err.println("Error has occured: "+error.getMessage());
		webSocket.request(1);
		Listener.super.onError(webSocket, error);
	}
	
}