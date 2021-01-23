package com.i2gether.beximcom.bkashpayment.bkashpayment.user.service;

import com.i2gether.beximcom.bkashpayment.bkashpayment.exception.AlreadyExistsException;
import com.i2gether.beximcom.bkashpayment.bkashpayment.exception.NotFoundException;
import com.i2gether.beximcom.bkashpayment.bkashpayment.user.entity.User;

public interface UserService {
	public User add(User user) throws AlreadyExistsException;
	public User get(Long id) throws NotFoundException; 
	public User modify(User user) throws NotFoundException;
	public void delete(Long id) throws NotFoundException;
	public Iterable<User> getAll();
}
