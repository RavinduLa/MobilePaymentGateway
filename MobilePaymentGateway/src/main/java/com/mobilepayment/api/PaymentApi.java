//IT19014128
//A.M.W.W.R.L. Wataketiya


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
	
	//Autowire the repositories
	@Autowired
	public PaymentApi(PaymentSubscriberRepository paymentSubsciberRepository, TransactionRepository transactionRepository)
	{
		this.paymentSubscriberRepository = paymentSubsciberRepository;
		this.transactionRepository = transactionRepository;
	}
	
	//make the payment
	public PaymentResponse makePayment(Payment payment) {
		
		//payment response object instantiation.
		PaymentResponse paymentResponse = new PaymentResponse();
		
		System.out.println();
		System.out.println("---TLS Mobile Payments---");
		System.out.println("---Payment request received---");
		System.out.println("---Begining process---");
		
		//get the user given phone number
		String userPhoneNumber = payment.getPhoneNumber();
		//get the user given PIN
		int userPin = payment.getPin();
		
		//find the paymentSubscriber for the user's number from the schema.
		PaymentSubscriber paymentSubscriber = paymentSubscriberRepository.findByMobileNumber(userPhoneNumber);
		
		
		if(paymentSubscriber == null) {
			//subscriber number is not in the schema
			//not registered for payments.
			
			//set the response status as failed.
			//set PIN and paymentSuccesful false
			System.out.println("The entered subscriber: " + userPhoneNumber + " is not subscribed for payments");
			paymentResponse.setMessage("Number is not subscribed for payments");
			paymentResponse.setStatus("falied");
			paymentResponse.setValidPin(false);
			paymentResponse.setPaymentSuccesful(false);
			
			//return the paymentResponse object.
			return paymentResponse;
		}
		else
		{
			//subscriber number is in the schema
			
			//validate the details forr the given number and the PIN.
			boolean pinMathced = validateDetails(userPin, paymentSubscriber);
			
			if(pinMathced)
			{
				//if user given PIN is matched process the transaction
				boolean transactionCompleted = processTransaction(payment.getAmount(), payment.getMerchantId(), userPhoneNumber);
				
				if(transactionCompleted)
				{
					//transaction is completed.
					System.out.println("Transaction completed......");
					System.out.println("Returning success to the merchant....");
					
					//set the paymentResponse status to success and message to Payment Successful
					//set the PIN and the PaymentSuccesful boolean values true
					paymentResponse.setStatus("success");
					paymentResponse.setMessage("Payment Succesful");
					paymentResponse.setValidPin(true);
					paymentResponse.setPaymentSuccesful(true);
					
					
					//return the payment response.
					return paymentResponse;
					
				}
				else
				{
					//transaction failed. Internal error.
					System.out.println("Transaction incomplete......");
					System.out.println("Returning failure to the merchant....");
					
					//set the payment status failed
					paymentResponse.setStatus("failed");
					paymentResponse.setMessage("Payment failed. Internal Error");
					
					//set the PIN as valid since PIN is validate at this point.
					paymentResponse.setValidPin(true);
					//set the payment is failed.
					paymentResponse.setPaymentSuccesful(false);
					
					//return the response
					return paymentResponse;
				}
			}
			else
			{
				//Pin is not verified
				System.out.println("Subscriber verification issue.....");
				System.out.println("Returning failure to the merchant....");
				
				//payment status is failed.
				paymentResponse.setStatus("failed");
				paymentResponse.setMessage("Payment failed. Pin mismatch or Subscriber payments disabled.");
				//set PIn as invalid
				paymentResponse.setValidPin(false);
				//set payment is unsuccessful
				paymentResponse.setPaymentSuccesful(false);
				
				//return the response
				return paymentResponse;
			}
			
		}
		
		
		
	}
	
	//method for validating the PIN 
	public boolean validateDetails(int userPin, PaymentSubscriber paymentSubscriber) {
		
		//check whether the subscriber is enabled in the schema
		if(paymentSubscriber.isEnabled())
		{
			//subscriber is enabled
			
			//get the original PIN
			int pin = paymentSubscriber.getPin();
			
			//compare the user given PIN with the original PIN
			if(userPin == pin)
			{
				//PIN matched with the original PIN. Return true.
				System.out.println("Pin matched.");
				return true;
			}
			else
			{
				//PIN did not match with the original PIN. Return false.
				System.out.println("Pin not matched.");
				System.out.println("Stored pin: "+ pin);
				System.out.println("Received pin: "+ userPin);
				
				return false;
			}
		}
		else
		{
			//the subscriber is not enabled for payments in the schema.
			System.out.println("The subscriber is not enabled for payments.");
			return false;
		}
		
	}
	
	//performing the transaction.
public boolean processTransaction(double amount, int merchantId, String mobileNumber) {
		
		//instantiate transaction object
		Transaction transaction =  new Transaction();
		
		//get the current time in a calendar instance
		Calendar current = Calendar.getInstance();
		
		
		transaction.setAmount(amount);  //set the transaction amount
		transaction.setMerchantId(merchantId); //set the merchant id
		transaction.setMobileNumber(mobileNumber);  //set the mobile number of the subscriber
		transaction.setTransactionTime(current);  //set the time stamp of the transaction
		
		if(transactionRepository.save(transaction) != null)
		{
			//transaction  is successful. Return true
			System.out.println("transaction registered succesfully");
			return true;
		}
		else
		{
			//transaction  is failed. Return false.
			System.out.println("Failed to register transaction");
			return false;
		}
		
		
	}

}
