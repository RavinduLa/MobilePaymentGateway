package com.mobilepayment.model;

import org.springframework.stereotype.Component;

@Component
public class PaymentResponse {

	private String status;  //payment status
	private boolean validPin;  //whether the pin is valid
	private boolean paymentSuccesful;  //whether the payment is successful
	private String message; //message
	
	public PaymentResponse() {
		
	}
	
	

	public PaymentResponse(String status, boolean validCard, boolean paymentSuccesful, String message) {
		super();
		this.status = status;
		this.validPin = validCard;
		this.paymentSuccesful = paymentSuccesful;
		this.message = message;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	public boolean isValidPin() {
		return validPin;
	}



	public void setValidPin(boolean validPin) {
		this.validPin = validPin;
	}



	public boolean isPaymentSuccesful() {
		return paymentSuccesful;
	}



	public void setPaymentSuccesful(boolean paymentSuccesful) {
		this.paymentSuccesful = paymentSuccesful;
	}



	@Override
	public String toString() {
		return "PaymentResponse [status=" + status + ", validCard=" + validPin + ", paymentSuccesful="
				+ paymentSuccesful + ", message=" + message + "]";
	}
}
