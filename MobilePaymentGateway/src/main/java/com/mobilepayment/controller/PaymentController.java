package com.mobilepayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobilepayment.dao.PaymentSubscriberRepository;
import com.mobilepayment.model.Payment;

@RestController
@RequestMapping("tls-pay/api")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class PaymentController {
	
	private PaymentSubscriberRepository paymentSubscriberRepository;
	
	@Autowired
	public PaymentController(PaymentSubscriberRepository paymentSubscriberRepository) {
		this.paymentSubscriberRepository = paymentSubscriberRepository;
	}
	
	
	@PostMapping(value="makePayment")
	public void makePayment(@RequestBody  Payment payment) {
		
	}

}
