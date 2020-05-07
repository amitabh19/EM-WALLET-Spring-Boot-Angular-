package com.wallet.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import com.wallet.entities.Person;
import com.wallet.entities.Transaction;
import com.wallet.repositories.TransactionRepository;
import com.wallet.repositories.UserRepository;
import com.wallet.service.UserService;

@SpringBootTest
@Transactional
class ServiceTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TransactionRepository transRepo;


	@Test
	@Rollback
	void testAddUser() {
		LocalDate d= LocalDate.now();
		Person p = new Person("emaihujl@email.com", "890898989", "test", 500.0, d, "testacc", "test9xyz", "testxyzw", new HashSet<Transaction>());
		Person p1 = userService.addUser(p);
		assertEquals(p, p1);
	} 
	
	@Test
	public void testGetUserById() {
		Optional<Person> p = userService.getUserById((long) 3);
		Person p1 = p .get();
		Person p2 = userRepo.getOne((long) 3);
		assertEquals(p1.getId(), p2.getId());
	}

	
	  @Test void testGetUsers() 
	  { 
		  List<Person> p = userRepo.findAll();
		  List<Person> p1 = userService.getUsers();
		  assertEquals(p, p1);
		  
	  }
	
	  @Rollback
	  @Test void testUpdateUser() 
	  {
		  Person p2 = userRepo.getOne((long) 2);
		  String name = "newTestName";
		  p2.setName(name);
		  userService.updateUser(p2);
		  p2 = userRepo.getOne((long) 2);
		  assertEquals(name, p2.getName());
	   }
	
	  @Rollback
	  @Test void testDeleteUser() {
		  Person p1 = userService.getUserById((long) 2).get();
		  
		  assertTrue(userService.deleteUser(p1));
	  
	  }
	 
	  @Test void testGetTransaction() { 
		  List<Transaction> t1 = transRepo.findAll();
		  List<Transaction> t2 = userService.getTransaction();
		  assertEquals(t1,t2);
	  }
	 
	 @Test void testGetTrans() { 
		 Person p1 = userService.getUserById((long) 2).get();
		  assertEquals(p1.getTransactions(), userService.getTrans(p1));
	 }
	 
	 @Rollback
	 @Test void testMakeTrans()
	 {
		 Transaction t = new Transaction("txn", 500.0, LocalDateTime.now(), "self deposit", (long) 2, (long) 2, 30000);
		 Transaction t1 = new Transaction("txn", 500.0, LocalDateTime.now(), "self withdraw", (long) 2, (long) 2, 30000);
		 Transaction t2 = new Transaction("txn", 500.0, LocalDateTime.now(), "debit", (long) 2, (long) 1, 30000);
		 assertEquals(userService.makeTrans(t), transRepo.save(t));
		 assertEquals(userService.makeTrans(t1), transRepo.save(t1));
		 assertEquals(userService.makeTrans(t2), transRepo.save(t2));
	 }
	 
}
