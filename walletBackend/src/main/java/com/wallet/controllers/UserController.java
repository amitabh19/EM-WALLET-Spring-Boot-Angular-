package com.wallet.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.entities.Person;
import com.wallet.entities.Transaction;
import com.wallet.repositories.TransactionRepository;
import com.wallet.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TransactionRepository transactionRepository;


	@GetMapping("/test")
	public String test() {
		LocalDate d = LocalDate.now();
		Set<Transaction> t = new HashSet<Transaction>();
		//new Person(emailId, mobileNo, name, balance, dob, accNo, username, password, transactions)
		/*
		 * Person p = new Person("amitabh19101998@gmail.com", "7550172933",
		 * "Amitabh Saxena", 24000.0, d, "AS7373", "am10i", "73747576", t); Person p1 =
		 * new Person("arjun@gmail.com", "85758288", "Arjun Saxena", 14000.0, d,
		 * "AS7374", "arj07", "9394959899", t); 
		 * 
		 * userRepository.save(p); userRepository.save(p1); userRepository.save(p2);
		 */
		userRepository.save(new Person("tes2@tes.com", "78992111", "test2", 5000.0, d, "Accoo","uname","pass1234", t ));
		return "done";
	}

	@GetMapping("/test1")
	public String test1() {
		LocalDateTime d = LocalDateTime.now();
		Person p1 = userRepository.getOne((long) 3);
		Person p2 = userRepository.getOne((long) 1);
		// transaction from p1 to p2 for 500.0
		p1.setBalance(p1.getBalance() - 500.0);
		p2.setBalance(p2.getBalance() + 500.0);
		String txnId = "TX" + d.toString();
		// Transaction t1 = new Transaction(500.0, d, "debit", p2.getId(), 0,
		// p1.getBalance());
		Transaction t = new Transaction(txnId, 500.0, d, "debit", p1.getId(), p2.getId(), p1.getBalance());
		p1.addTransaction(t);
		userRepository.save(p1);
		t.settType("credit");
		t.setUpdatedPersonBal(p2.getBalance());
		p2.addTransaction(t);
		userRepository.save(p2);
		return "transaction success";
	}

	/*
	 * @GetMapping("/test2") public String test2() { LocalDate d = LocalDate.now();
	 * Person p1 = userRepository.getOne((long) 1); Person p2 =
	 * userRepository.getOne((long) 3); //transaction from p1 to p2 for 500.0
	 * p1.setBalance(p1.getBalance()-500.0); p2.setBalance(p2.getBalance()+500.0);
	 * Transaction t1 = new Transaction(500.0, d, "debit", p2.getId(),
	 * p1.getBalance()); Transaction t2 = new Transaction(500, d, "credit",
	 * p1.getId(), p2.getBalance()); p1.addTransaction(t1); p2.addTransaction(t2);
	 * 
	 * userRepository.save(p1); userRepository.save(p2); return
	 * "transaction success"; }
	 */
	@GetMapping("/users")
	public List<Person> getUsers() {
		System.out.println(userRepository.findAll().size());
		return userRepository.findAll();
	}

	

	@GetMapping("/trans")
	public List<Transaction> getTrans() {
		//  System.out.println(userRepository.findAll().size());
		return transactionRepository.findAll();
	}

	@GetMapping("/trans/{id}")
	public Set<Transaction> getTrans(@PathVariable Long id) {
		Optional<Person> p = userRepository.findById(id);
		Person p1 = p.get();
		return p1.getTransactions();
	}

	@GetMapping("/user/{id}")
	public Optional<Person> getUser(@PathVariable Long id) {
		return userRepository.findById(id);
	}
	
	@GetMapping("/user/{sid}/{rid}/{amount}")
	public String trasact(@PathVariable Long sid, @PathVariable Long rid, @PathVariable Double amount) {
		LocalDateTime d = LocalDateTime.now();
		Person p1 = userRepository.getOne(sid);
		Person p2 = userRepository.getOne(rid);
		p1.setBalance(p1.getBalance() - amount);
		p2.setBalance(p2.getBalance() + amount);
		String d1 = d.toString();
		String d2 = d1.replace(":", "");
		String d3 = d2.replace("-", "");
		String d4 = d3.replace(".", "");
		String txnId = "TX" + d4;
		Transaction t = new Transaction(txnId, amount, d, "debit", p1.getId(), p2.getId(), p1.getBalance());
		p1.addTransaction(t);
		userRepository.save(p1);
		t.settType("credit");
		t.setUpdatedPersonBal(p2.getBalance());
		p2.addTransaction(t);
		userRepository.save(p2);
		return "transaction success";
	}

	@GetMapping("/depo/{sid}/{amount}")
	public String depo(@PathVariable Long sid, @PathVariable Double amount) {
		LocalDateTime d = LocalDateTime.now();
		Person p1 = userRepository.getOne(sid);
		p1.setBalance(p1.getBalance() + amount);
		String d1 = d.toString();
		String d2 = d1.replace(":", "");
		String d3 = d2.replace("-", "");
		String d4 = d3.replace(".", "");
		String txnId = "TX" + d4;
		Transaction t = new Transaction(txnId, amount, d, "self deposit", p1.getId(), p1.getId(), p1.getBalance());
		p1.addTransaction(t);
		userRepository.save(p1);
		return "transaction success";
	}
	
	@GetMapping("/with/{sid}/{amount}")
	public String with(@PathVariable Long sid, @PathVariable Double amount) {
		LocalDateTime d = LocalDateTime.now();
		Person p1 = userRepository.getOne(sid);
		p1.setBalance(p1.getBalance() - amount);
		String d1 = d.toString();
		String d2 = d1.replace(":", "");
		String d3 = d2.replace("-", "");
		String d4 = d3.replace(".", "");
		String txnId = "TX" + d4;
		Transaction t = new Transaction(txnId, amount, d, "self withdraw", p1.getId(), p1.getId(), p1.getBalance());
		p1.addTransaction(t);
		userRepository.save(p1);
		return "transaction success";
	}
	
	@DeleteMapping("/user/{id}")
	public boolean deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
		return true;
	}

	@PutMapping("/user")
	public Person updateUser(Person user) {
		return userRepository.save(user);

	}

	@PostMapping("/user")
	public String createUser(Person user, @RequestParam("dob") 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {

		user.setDob(localDate);
		System.out.println(user.toString());
		return userRepository.save(user).toString();

	}

	@GetMapping("/test3")
	public String test3() {
		LocalDateTime d = LocalDateTime.now();

		return "TX" + d.toString();
	}

	/*
	 * @PostMapping("/auth") public @ResponseBody Test addNewWorker(@RequestBody
	 * Test jsonString) {
	 * 
	 * //do business logic return test; }
	 */

}
