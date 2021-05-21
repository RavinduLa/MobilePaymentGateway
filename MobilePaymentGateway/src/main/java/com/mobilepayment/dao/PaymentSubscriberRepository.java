//IT19014128
//A.M.W.W.R.L. Wataketiya

package com.mobilepayment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobilepayment.model.PaymentSubscriber;

public interface PaymentSubscriberRepository extends JpaRepository<PaymentSubscriber, Integer> {

	public PaymentSubscriber findByMobileNumber(String mobileNumber);
}
