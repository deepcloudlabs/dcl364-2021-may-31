package com.example.crm.application.business.event;

import com.example.crm.domain.TcKimlikNo;

public class CustomerReleasedEvent extends CustomerEvent {

	public CustomerReleasedEvent(TcKimlikNo kimlik) {
		super(kimlik);
	}

}
