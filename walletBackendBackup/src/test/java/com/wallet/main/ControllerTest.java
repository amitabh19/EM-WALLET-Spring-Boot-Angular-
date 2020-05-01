package com.wallet.main;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.controllers.UserController;
import com.wallet.entities.Person;
import com.wallet.entities.Transaction;
import com.wallet.repositories.TransactionRepository;
import com.wallet.repositories.UserRepository;

@SpringBootTest
@Transactional
public class ControllerTest {

	@Autowired
	private UserController uc;
	@Autowired
	private UserRepository ur;
	@Autowired
	private TransactionRepository tr;
	
	@Test
	public void testGetUsers() {
		assertEquals(uc.getUsers(),ur.findAll());
	}

	@Test
	public void testGetTrans() {
		assertEquals(uc.getTrans(),tr.findAll());
	}

	@Test
	public void testGetUser() {
		assertEquals(uc.getUser((long) 2), ur.findById((long) 2));
	}

	@Test
	public void testUpdateUser() {
		  Person p2 = ur.getOne((long) 2);
		  String name = "newTestName";
		  p2.setName(name);
		  uc.updateUser(p2);
		  p2 = ur.getOne((long) 2);
		  assertEquals(name, p2.getName());	}
    
	@Rollback
	@Test
	public void testAddCustomer() {
		LocalDate d= LocalDate.now();
		Person p = new Person("emaihujl@email.com", "890898989", "test", 500.0, d, "testacc", "test9xyz", "testxyzw", new HashSet<Transaction>());
		Person p1 = uc.addCustomer(p);
		assertEquals(p, p1);
	}
	
	@Rollback
	 @Test void testAddTrans()
	 {
		 Transaction t = new Transaction("txn", 500.0, LocalDateTime.now(), "self deposit", (long) 2, (long) 2, 30000);
		 Transaction t1 = new Transaction("txn", 500.0, LocalDateTime.now(), "self withdraw", (long) 2, (long) 2, 30000);
		 Transaction t2 = new Transaction("txn", 500.0, LocalDateTime.now(), "debit", (long) 2, (long) 1, 30000);
		 assertEquals(uc.addTrans(t), tr.save(t));
		 assertEquals(uc.addTrans(t1), tr.save(t1));
		 assertEquals(uc.addTrans(t2), tr.save(t2));
	 }
}
