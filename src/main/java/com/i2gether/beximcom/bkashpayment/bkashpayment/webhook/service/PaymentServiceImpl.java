package com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2gether.beximcom.bkashpayment.bkashpayment.exception.AlreadyExistsException;
import com.i2gether.beximcom.bkashpayment.bkashpayment.exception.NotFoundException;
import com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.entity.Payment;
import com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	public Payment add(Payment payment) throws AlreadyExistsException {
		return paymentRepository.save(payment);
	}

	@Override
	public Payment get(Long id) throws NotFoundException {
				
		return paymentRepository.findById(id).orElseThrow(() -> new NotFoundException());
	}

	@Override
	public Payment modify(Payment user) throws NotFoundException {
		this.get(user.getId());
		
		return paymentRepository.save(user);
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		
		Payment user = this.get(id);
		paymentRepository.delete(user);
		
	}

	@Override
	public Iterable<Payment> getAll() {
		return paymentRepository.findAll();
	}

}
