package com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.service;

import com.i2gether.beximcom.bkashpayment.bkashpayment.exception.AlreadyExistsException;
import com.i2gether.beximcom.bkashpayment.bkashpayment.exception.NotFoundException;
import com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.entity.Payment;

public interface PaymentService {
	public Payment add(Payment user) throws AlreadyExistsException;
	public Payment get(Long id) throws NotFoundException; 
	public Payment modify(Payment user) throws NotFoundException;
	public void delete(Long id) throws NotFoundException;
	public Iterable<Payment> getAll();
}
