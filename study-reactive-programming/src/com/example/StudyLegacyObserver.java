package com.example;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import com.example.event.TradeEvent;

@SuppressWarnings("deprecation")
public class StudyLegacyObserver {

	public static void main(String[] args) {
		Observable observable = new TradeEventObservable();
		Observer slowObserver = (o,tradeEvent) -> {
			try { TimeUnit.SECONDS.sleep(3);}catch(Exception e) {}
			System.err.println("Slow observer: "+tradeEvent);
		};
		Observer fastObserver = (o,tradeEvent) -> {
			System.err.println("Fast observer: "+tradeEvent);
		};
		observable.addObserver(fastObserver);
		observable.addObserver(slowObserver);
		var tradeEvents = List.of(
				new TradeEvent("msft", 100, 10),
				new TradeEvent("orcl", 120, 20),
				new TradeEvent("ibm", 70, 30),
				new TradeEvent("msft", 101, 50),
				new TradeEvent("orcl", 122, 70),
				new TradeEvent("ibm", 73, 60)
		);
		tradeEvents.forEach(observable::notifyObservers);
	}

}

@SuppressWarnings("deprecation")
class TradeEventObservable extends Observable {

	@Override
	public void notifyObservers(Object event) {
		setChanged();
		super.notifyObservers(event);
	}

}

