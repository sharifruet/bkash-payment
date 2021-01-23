package com.i2gether.beximcom.bkashpayment.bkashpayment.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.i2gether.beximcom.bkashpayment.bkashpayment.user.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
