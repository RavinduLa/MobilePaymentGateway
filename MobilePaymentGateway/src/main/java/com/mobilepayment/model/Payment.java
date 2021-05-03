package com.mobilepayment.model;

public class Payment {
	
	private String phoneNumer;
	private int pin;
	private double amount;
	private int merchantId;
	
	public Payment() {
		
	}
	
	public Payment(String phoneNumer, int pin, double amount, int merchantId) {
		super();
		this.phoneNumer = phoneNumer;
		this.pin = pin;
		this.amount = amount;
		this.merchantId = merchantId;
	}

	public String getPhoneNumer() {
		return phoneNumer;
	}

	public void setPhoneNumer(String phoneNumer) {
		this.phoneNumer = phoneNumer;
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
		return "Payment [phoneNumer=" + phoneNumer + ", pin=" + pin + ", amount=" + amount + ", merchantId="
				+ merchantId + "]";
	}
	
	
	
	
	

}
