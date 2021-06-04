package com.example.crm.application.business.event;

import com.example.crm.domain.TcKimlikNo;

public class CustomerAcquiredEvent extends CustomerEvent {

	public CustomerAcquiredEvent(TcKimlikNo kimlik) {
		super(kimlik);
	}

}
