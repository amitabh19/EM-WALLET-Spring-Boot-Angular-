package com.wallet.controllers;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.wallet.entities.Person;
import com.wallet.entities.Transaction;

import com.wallet.service.UserService;
import com.wallet.exceptions.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService ;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@GetMapping("/users")
	public List<Person> getUsers() throws ListNotFoundException{
		logger.trace("getUser() method in controller class is accessed");
		if(userService.getUsers()==null)
		{
			ListNotFoundException listNotFound = new ListNotFoundException("User List is empty");
			throw listNotFound;
		}
		return userService.getUsers();
	}

	@GetMapping("/trans")
	public List<Transaction> getTrans() throws ListNotFoundException{
		logger.trace("getTrans() method in controller class is accessed");
		if(userService.getTransaction()==null)
		{
			ListNotFoundException listNotFound = new ListNotFoundException("Transaction List is empty");
			throw listNotFound;
		}
		return userService.getTransaction();
	}

	@GetMapping("/trans/{id}")
	public Set<Transaction> getTrans(@PathVariable Long id) throws ListNotFoundException{
		logger.trace("getTransbyUserid() method in controller class is accessed");
		Person p = userService.getUserById(id).get();
		if(userService.getTrans(p)==null)
		{
			ListNotFoundException listNotFound = new ListNotFoundException("Transaction List for this account is empty");
			throw listNotFound;
		}
		return userService.getTrans(p);
	}

	@GetMapping("/user/{id}")
	public Person getUser(@PathVariable Long id) throws UserNotFoundException {

		logger.trace("getUser() method in controller class is accessed");
		if(userService.getUserById(id).get() == null)
		{
			UserNotFoundException unfe = new UserNotFoundException("No user found");
			throw unfe;
		}
		return userService.getUserById(id).get();
	}

	@DeleteMapping("/user/{id}")
	public boolean deleteUser(@PathVariable Long id) {
		logger.trace("deleteUser() method in controller class is accessed");
		Optional<Person> p =  userService.getUserById(id);
		Person p1 = p.get();
		userService.deleteUser(p1);
		return true;
	}

	@PutMapping("/user")
	public Person updateUser(Person user) {
		logger.trace("updateUser() method in controller class is accessed");
		return userService.updateUser(user);
	}

	@PostMapping("/addCustomer")
	public Person addCustomer(@RequestBody Person c) {
		logger.trace("addCustomer() method in controller class is accessed");
		String s1 = c.getName().charAt(0) + c.getName().charAt(1) + LocalDate.now().toString();
		String s2 = stringCleaner(s1);
		c.setAccNo(s2);
		return userService.addUser(c);
	}

	@PostMapping("/addTrans")
	public Transaction addTrans(@RequestBody Transaction t) {
		logger.trace("addTrans() method in controller class is accessed");
		return userService.makeTrans(t);
	}
	
	public String stringCleaner(String d)
	{
		String temp_d2 = d.replace(":", "");
		String temp_d3 = temp_d2.replace("-", "");
		String temp_d4 = temp_d3.replace(".", "");
		return temp_d4;
		
	}

}
