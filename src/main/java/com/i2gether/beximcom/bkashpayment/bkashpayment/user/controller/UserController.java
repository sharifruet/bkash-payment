package com.i2gether.beximcom.bkashpayment.bkashpayment.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.i2gether.beximcom.bkashpayment.bkashpayment.exception.AlreadyExistsException;
import com.i2gether.beximcom.bkashpayment.bkashpayment.exception.NotFoundException;
import com.i2gether.beximcom.bkashpayment.bkashpayment.user.entity.User;
import com.i2gether.beximcom.bkashpayment.bkashpayment.user.service.UserService;

@Controller 
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping
	public String showUserList(Model model) {
	    model.addAttribute("users", userService.getAll());
	    return "user-list";
	}
    
    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }
    
    @PostMapping("/add")
    public String addUser(@Validated User user, BindingResult result, Model model) throws AlreadyExistsException {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        userService.add(user);
        return "redirect:/user";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) throws NotFoundException {
        User user = userService.get(id);
        
        model.addAttribute("user", user);
        return "update-user";
    }
    
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Validated User user, 
      BindingResult result, Model model) throws NotFoundException {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
            
        userService.modify(user);
        return "redirect:/index";
    }
        
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) throws NotFoundException {
    	userService.delete(id);
        return "redirect:/index";
    }

}