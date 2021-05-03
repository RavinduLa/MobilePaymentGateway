package com.mobilepayment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="payment_subscriber")
public class PaymentSubscriber {
	
	@Id
	@Column(name="nic")
	private String NIC;
	
	@Column(name="subscriber_name")
	private String subscriberName;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="pin")
	private int pin;
	
	public PaymentSubscriber() {
		
	}
	
	public PaymentSubscriber(String nIC, String subscriberName, String mobileNumber, int pin) {
		super();
		NIC = nIC;
		this.subscriberName = subscriberName;
		this.mobileNumber = mobileNumber;
		this.pin = pin;
	}

	public String getNIC() {
		return NIC;
	}

	public void setNIC(String nIC) {
		NIC = nIC;
	}

	public String getName() {
		return subscriberName;
	}

	public void setName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "PaymentSubscriber [NIC=" + NIC + ", name=" + subscriberName + ", mobileNumber=" + mobileNumber + ", pin=" + pin
				+ "]";
	}
	
	
	
	

}