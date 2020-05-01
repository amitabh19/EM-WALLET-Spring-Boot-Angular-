package com.wallet.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.dao.DaoInterface;
import com.wallet.dao.DaoInterfaceImpl;
import com.wallet.entities.Person;
import com.wallet.entities.Transaction;
import com.wallet.repositories.TransactionRepository;
import com.wallet.repositories.UserRepository;
import com.wallet.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService ;
	
	
	
	@GetMapping("/users")
	public List<Person> getUsers() {
		return userService.getUsers();
	}

	@GetMapping("/trans")
	public List<Transaction> getTrans() {
		return userService.getTransaction();
	}

	@GetMapping("/trans/{id}")
	public Set<Transaction> getTrans(@PathVariable Long id) {
		Optional<Person> p = userService.getUserById(id);
		Person p1 = p.get();
		return userService.getTrans(p1);
	}

	@GetMapping("/user/{id}")
	public Optional<Person> getUser(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@GetMapping("/user/{sid}/{rid}/{amount}")
	public String trasact(@PathVariable Long sid, @PathVariable Long rid, @PathVariable Double amount) {
		LocalDateTime d = LocalDateTime.now();
		Optional<Person> p = userService.getUserById(sid);
		Person p1 = p.get();
		
		Optional<Person> b = userService.getUserById(rid);
		Person p2 = b.get();
		p1.setBalance(p1.getBalance() - amount);
		p2.setBalance(p2.getBalance() + amount);
		String temp_d1 = stringCleaner(d.toString());
		String txnId = "TX" + temp_d1 + p1.getId();
		Transaction t = new Transaction(txnId, amount, d, "debit", p1.getId(), p2.getId(), p1.getBalance());
		p1.addTransaction(t);
		userService.updateUser(p1);
		t.settType("credit");
		t.setUpdatedPersonBal(p2.getBalance());
		p2.addTransaction(t);
		userService.updateUser(p2);
		return "transaction success";
	}

	@GetMapping("/depo/{sid}/{amount}")
	public String depo(@PathVariable Long sid, @PathVariable Double amount) {
		LocalDateTime d = LocalDateTime.now();
		Optional<Person> p = userService.getUserById(sid);
		Person p1 = p.get();
		p1.setBalance(p1.getBalance() + amount);
		String temp_d1 = stringCleaner(d.toString());
		String txnId = "TX" + temp_d1 + p1.getId();
		Transaction t = new Transaction(txnId, amount, d, "self deposit", p1.getId(), p1.getId(), p1.getBalance());
		p1.addTransaction(t);
		userService.updateUser(p1);
		return "transaction success";
	}

	@GetMapping("/with/{sid}/{amount}")
	public String with(@PathVariable Long sid, @PathVariable Double amount) {
		LocalDateTime d = LocalDateTime.now();
		Person p1 = userService.getUserById(sid).get();
		p1.setBalance(p1.getBalance() - amount);
		String temp_d1 = stringCleaner(d.toString());
		String txnId = "TX" + temp_d1 + p1.getId();
		Transaction t = new Transaction(txnId, amount, d, "self withdraw", p1.getId(), p1.getId(), p1.getBalance());
		p1.addTransaction(t);
		userService.updateUser(p1);
		return "transaction success";
	}

	@DeleteMapping("/user/{id}")
	public boolean deleteUser(@PathVariable Long id) {
		Optional<Person> p =  userService.getUserById(id);
		Person p1 = p.get();
		userService.deleteUser(p1);
		return true;
	}

	@PutMapping("/user")
	public Person updateUser(Person user) {
		return userService.updateUser(user);
	}

	@PostMapping("/addCustomer")
	public Person addCustomer(@RequestBody Person c) {

		String s1 = c.getName().charAt(0) + c.getName().charAt(1) + LocalDate.now().toString();
		String s2 = stringCleaner(s1);
		c.setAccNo(s2);
		Person c1 = userService.addUser(c);
		System.out.println("Customer added to datbase");
		return c1;
	}

	public String stringCleaner(String d)
	{
		String temp_d2 = d.replace(":", "");
		String temp_d3 = temp_d2.replace("-", "");
		String temp_d4 = temp_d3.replace(".", "");
		return temp_d4;
		
	}

}