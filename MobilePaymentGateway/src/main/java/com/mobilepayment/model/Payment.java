package com.mobilepayment.model;

public class Payment {
	
	private String phoneNumber;
	private int pin;
	private double amount;
	private int merchantId;
	
	public Payment() {
		
	}
	
	public Payment(String phoneNumber, int pin, double amount, int merchantId) {
		super();
		this.phoneNumber = phoneNumber;
		this.pin = pin;
		this.amount = amount;
		this.merchantId = merchantId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	@Override
	public String toString() {
		return "Payment [phoneNumer=" + phoneNumber + ", pin=" + pin + ", amount=" + amount + ", merchantId="
				+ merchantId + "]";
	}
	
	
	
	
	

}
