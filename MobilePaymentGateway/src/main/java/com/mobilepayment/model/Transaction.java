package com.mobilepayment.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@Column(name="transaction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="merchant_id")
	private int merchantId;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="transaction_date")
	private Calendar transactionTime;
	
	
	public Transaction() {
		
	}
	

	public Transaction(int transactionId, String mobileNumber, int mearchantId, double amount,
			Calendar transactionDate) {
		super();
		this.transactionId = transactionId;
		this.mobileNumber = mobileNumber;
		this.merchantId = mearchantId;
		this.amount = amount;
		this.transactionTime = transactionDate;
	}






	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int mearchantId) {
		this.merchantId = mearchantId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

	public int getTransactionId() {
		return transactionId;
	}



	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
	



	public Calendar getTransactionTime() {
		return transactionTime;
	}






	public void setTransactionTime(Calendar transactionDate) {
		this.transactionTime = transactionDate;
	}






	@Override
	public String toString() {
		return "Trasnaction [transactionId=" + transactionId + ", mobileNumber=" + mobileNumber + ", mearchantId="
				+ merchantId + ", amount=" + amount + ", transactionDate=" + transactionTime + "]";
	}
	
	
	
	

}
