//IT19014128
//A.M.W.W.R.L. Wataketiya


package com.mobilepayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobilepayment.api.PaymentApi;

import com.mobilepayment.model.Payment;
import com.mobilepayment.model.PaymentResponse;


@RestController
@RequestMapping("tls-pay/api")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class PaymentController {
	
	private PaymentApi paymentApi;
	
	
	//autowires the API
	@Autowired
	public PaymentController(PaymentApi paymentApi) {
		this.paymentApi = paymentApi;
	}
	
	
	
	//receive the request here.
	@PostMapping(value="makePayment")
	public PaymentResponse makePayment(@RequestBody  Payment payment) {
		
		//Payment API is called here
		PaymentResponse paymentResponse = paymentApi.makePayment(payment);
		
		//return the response given by the API.
		return paymentResponse;
		
	}
	
	

}
