package com.example.crm.application.business.event;

import com.example.crm.domain.BusinessEvent;
import com.example.crm.domain.TcKimlikNo;

@BusinessEvent
public abstract class CustomerEvent {
	protected TcKimlikNo kimlik;

	public CustomerEvent(TcKimlikNo kimlik) {
		this.kimlik = kimlik;
	}

	public TcKimlikNo getKimlik() {
		return kimlik;
	}

}
