package com.wallet.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.fail;

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
import com.wallet.dao.DaoInterface;
import com.wallet.entities.Person;
import com.wallet.entities.Transaction;
import com.wallet.repositories.TransactionRepository;
import com.wallet.repositories.UserRepository;

@SpringBootTest
@Transactional
class DaoTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private DaoInterface dao;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TransactionRepository transRepo;


	@Test
	@Rollback
	void testAddUser() {
		LocalDate d= LocalDate.now();
		Person p = new Person("emaihujl@email.com", "890898989", "test", 500.0, d, "testacc", "test9xyz", "testxyzw", new HashSet<Transaction>());
		Person p1 = dao.addUser(p);
		assertEquals(p, p1);
	} 
	
	@Test
	public void testGetUserById() {
		Optional<Person> p = dao.getUserById((long) 3);
		Person p1 = p .get();
		Person p2 = userRepo.getOne((long) 3);
		assertEquals(p1.getId(), p2.getId());
	}

	
	  @Test void testGetUsers() 
	  { 
		  List<Person> p = userRepo.findAll();
		  List<Person> p1 = dao.getUsers();
		  assertEquals(p, p1);
		  
	  }
	
	  @Rollback
	  @Test void testUpdateUser() 
	  {
		  Person p2 = userRepo.getOne((long) 2);
		  String name = "newTestName";
		  p2.setName(name);
		  dao.updateUser(p2);
		  p2 = userRepo.getOne((long) 2);
		  assertEquals(name, p2.getName());
	   }
	
	  @Rollback
	  @Test void testDeleteUser() {
		  Person p1 = dao.getUserById((long) 2).get();
		  dao.deleteUser(p1);
		  assertNull(userRepo.getOne((long) 2).getId());
	  
	  }
	 
	  @Test void testGetTransaction() { 
		  List<Transaction> t1 = transRepo.findAll();
		  List<Transaction> t2 = dao.getTransaction();
		  assertEquals(t1,t2);
	  }
	 
	 @Test void testGetTrans() { 
		 Person p1 = dao.getUserById((long) 2).get();
		  assertEquals(p1.getTransactions(), dao.getTrans(p1));
	 }
	 
	 @Rollback
	 @Test void testMakeTrans()
	 {
		 Transaction t = new Transaction("txn", 500.0, LocalDateTime.now(), "self deposit", (long) 2, (long) 2, 30000);
		 Transaction t1 = new Transaction("txn", 500.0, LocalDateTime.now(), "self withdraw", (long) 2, (long) 2, 30000);
		 Transaction t2 = new Transaction("txn", 500.0, LocalDateTime.now(), "debit", (long) 2, (long) 1, 30000);
		 assertEquals(dao.makeTrans(t), transRepo.save(t));
		 assertEquals(dao.makeTrans(t1), transRepo.save(t1));
		 assertEquals(dao.makeTrans(t2), transRepo.save(t2));
	 }
	 
}
