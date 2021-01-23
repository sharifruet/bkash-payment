package com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.i2gether.beximcom.bkashpayment.bkashpayment.user.entity.User;
import com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.entity.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
