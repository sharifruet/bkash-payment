package com.i2gether.beximcom.bkashpayment.bkashpayment.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2gether.beximcom.bkashpayment.bkashpayment.exception.AlreadyExistsException;
import com.i2gether.beximcom.bkashpayment.bkashpayment.exception.NotFoundException;
import com.i2gether.beximcom.bkashpayment.bkashpayment.user.entity.User;
import com.i2gether.beximcom.bkashpayment.bkashpayment.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User add(User user) throws AlreadyExistsException {
		return userRepository.save(user);
	}

	@Override
	public User get(Long id) throws NotFoundException {
				
		return userRepository.findById(id).orElseThrow(() -> new NotFoundException());
	}

	@Override
	public User modify(User user) throws NotFoundException {
		this.get(user.getId());
		
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		
		User user = this.get(id);
		userRepository.delete(user);
		
	}

	@Override
	public Iterable<User> getAll() {
		return userRepository.findAll();
	}

}
