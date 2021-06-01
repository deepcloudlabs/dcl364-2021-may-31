package com.example;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

import com.example.event.TradeEvent;


public class StudyReactive9 {

	public static void main(String[] args) throws Exception {
		SubmissionPublisher<TradeEvent> publisher = new SubmissionPublisher<>();
		var slowSubscriber = new AlgoTrader();
		var fastSubscriber = new SignalProcessor();
		publisher.subscribe(slowSubscriber);
		publisher.subscribe(fastSubscriber);
		var tradeEvents = List.of(
				new TradeEvent("msft", 100, 10),
				new TradeEvent("orcl", 120, 20),
				new TradeEvent("ibm", 70, 30),
				new TradeEvent("msft", 101, 50),
				new TradeEvent("orcl", 122, 70),
				new TradeEvent("ibm", 73, 60)
		);
		tradeEvents.forEach(publisher::submit);
		TimeUnit.SECONDS.sleep(30);
		System.err.println("Done!");
		publisher.close();
	}

}

class AlgoTrader implements Flow.Subscriber<TradeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		System.err.println("AlgoTrader ["+Thread.currentThread().getName()+"]: "+event);
		try { TimeUnit.SECONDS.sleep(3);}catch(Exception e) {}
		subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println("Error has occured in AlgoTrader: "+throwable.getMessage());		
	}

	@Override
	public void onComplete() {
		System.err.println("AlgoTrader is completed!");
		
	}
	
}

class SignalProcessor implements Flow.Subscriber<TradeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		System.err.println("SignalProcessor ["+Thread.currentThread().getName()+"]: "+event);
		subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println("Error has occured in SignalProcessor: "+throwable.getMessage());		
	}

	@Override
	public void onComplete() {
		System.err.println("SignalProcessor is completed!");
		
	}
	
}
