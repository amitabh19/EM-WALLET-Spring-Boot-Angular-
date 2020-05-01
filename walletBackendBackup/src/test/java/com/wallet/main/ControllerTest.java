package com.wallet.main;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.controllers.UserController;
import com.wallet.repositories.UserRepository;

@SpringBootTest
@Transactional
public class ControllerTest {

	@Autowired
	private UserController uc;
	@Autowired
	private UserRepository ur;
	@Test
	public void testTest2() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUsers() {
		assertEquals(uc.getUsers(),ur.findAll());
	}

	@Test
	public void testGetTrans() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTransLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testTrasact() {
		fail("Not yet implemented");
	}

	@Test
	public void testDepo() {
		fail("Not yet implemented");
	}

	@Test
	public void testWith() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCustomer() {
		fail("Not yet implemented");
	}

}
