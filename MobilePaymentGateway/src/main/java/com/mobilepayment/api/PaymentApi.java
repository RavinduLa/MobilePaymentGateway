package com.mobilepayment.api;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilepayment.dao.PaymentSubscriberRepository;
import com.mobilepayment.dao.TransactionRepository;
import com.mobilepayment.model.Payment;
import com.mobilepayment.model.PaymentResponse;
import com.mobilepayment.model.PaymentSubscriber;
import com.mobilepayment.model.Transaction;

@Service
public class PaymentApi {
	
	private PaymentSubscriberRepository paymentSubscriberRepository;
	
	private TransactionRepository transactionRepository;
	
	@Autowired
	public PaymentApi(PaymentSubscriberRepository paymentSubsciberRepository, TransactionRepository transactionRepository)
	{
		this.paymentSubscriberRepository = paymentSubsciberRepository;
		this.transactionRepository = transactionRepository;
	}
	
	public PaymentResponse makePayment(Payment payment) {
		
		PaymentResponse paymentResponse = new PaymentResponse();
		
		System.out.println();
		System.out.println("---TLS Mobile Payments---");
		System.out.println("---Payment request received---");
		System.out.println("---Begining process---");
		
		String userPhoneNumber = payment.getPhoneNumer();
		int userPin = payment.getPin();
		PaymentSubscriber paymentSubscriber = paymentSubscriberRepository.findByMobileNumber(userPhoneNumber);
		boolean pinMathced = validateDetails(userPin, paymentSubscriber);
		
		if(pinMathced)
		{
			boolean transactionCompleted = processTransaction(payment.getAmount(), payment.getMerchantId(), userPhoneNumber);
			
			if(transactionCompleted)
			{
				System.out.println("Transaction completed......");
				System.out.println("Returning success to the merchant....");
				
				paymentResponse.setStatus("success");
				paymentResponse.setMessage("payment succesful");
				paymentResponse.setValidPin(true);
				paymentResponse.setPaymentSuccesful(true);
				return paymentResponse;
				
			}
			else
			{
				System.out.println("Transaction incomplete......");
				System.out.println("Returning failure to the merchant....");
				
				
				paymentResponse.setStatus("failed");
				paymentResponse.setMessage("payment failed");
				paymentResponse.setValidPin(true);
				paymentResponse.setPaymentSuccesful(false);
				return paymentResponse;
			}
		}
		else
		{
			System.out.println("Subscriber verification issue.....");
			System.out.println("Returning failure to the merchant....");
			
			paymentResponse.setStatus("failed");
			paymentResponse.setMessage("payment failed");
			paymentResponse.setValidPin(false);
			paymentResponse.setPaymentSuccesful(false);
			return paymentResponse;
		}
		
	}
	
	public boolean validateDetails(int userPin, PaymentSubscriber paymentSubscriber) {
		
		if(paymentSubscriber.isEnabled())
		{
			
			int pin = paymentSubscriber.getPin();
			
			if(userPin == pin)
			{
				System.out.println("Pin matched.");
				return true;
			}
			else
			{
				System.out.println("Pin not matched.");
				System.out.println("Stored pin: "+ pin);
				System.out.println("Received pin: "+ userPin);
				
				return false;
			}
		}
		else
		{
			System.out.println("The subscriber is not enabled for payments.");
			return false;
		}
		
	}
	
public boolean processTransaction(double amount, int merchantId, String mobileNumber) {
		
		Transaction transaction =  new Transaction();
		Calendar current = Calendar.getInstance();
		transaction.setAmount(amount);
		transaction.setMerchantId(merchantId);
		transaction.setMobileNumber(mobileNumber);
		transaction.setTransactionTime(current);
		
		if(transactionRepository.save(transaction) != null)
		{
			System.out.println("transaction registered succesfully");
			return true;
		}
		else
		{
			System.out.println("Failed to register transaction");
			return false;
		}
		
		
	}

}
