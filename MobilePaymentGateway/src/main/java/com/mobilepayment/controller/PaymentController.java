package com.mobilepayment.controller;



import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobilepayment.dao.PaymentSubscriberRepository;
import com.mobilepayment.dao.TransactionRepository;
import com.mobilepayment.model.Payment;
import com.mobilepayment.model.PaymentSubscriber;
import com.mobilepayment.model.Transaction;

@RestController
@RequestMapping("tls-pay/api")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class PaymentController {
	
	@Autowired
	private PaymentSubscriberRepository paymentSubscriberRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	/*@Autowired
	public PaymentController(PaymentSubscriberRepository paymentSubscriberRepository, TransactionRepository transactionRepository) {
		this.paymentSubscriberRepository = paymentSubscriberRepository;
		this.transactionRepository = transactionRepository;
	}*/
	
	
	@PostMapping(value="makePayment")
	public boolean makePayment(@RequestBody  Payment payment) {
		
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
				System.out.println("Returning true to the merchant....");
				return true;
				
			}
			else
			{
				System.out.println("Transaction incomplete......");
				System.out.println("Returning false to the merchant....");
				return false;
			}
		}
		else
		{
			System.out.println("Subscriber verification issue.....");
			System.out.println("Returning false to the merchant....");
			return false;
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
