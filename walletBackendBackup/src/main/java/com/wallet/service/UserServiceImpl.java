package com.wallet.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.controllers.UserController;
import com.wallet.dao.DaoInterface;
import com.wallet.dao.DaoInterfaceImpl;
import com.wallet.entities.Person;
import com.wallet.entities.Transaction;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private DaoInterface dao;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Override
	public Person addUser(Person p) {
		logger.trace("addUser() method in service class is accessed");
		return dao.addUser(p);
	}

	@Override
	public Optional<Person> getUserById(Long id) {
		logger.trace("getUserById() method in service class is accessed");
		return dao.getUserById(id);
	}

	@Override
	public Person updateUser(Person p) {
		logger.trace("updateUser() method in service class is accessed");
		return dao.updateUser(p);
	}

	@Override
	public boolean deleteUser(Person p) {
		logger.trace("deleteUser() method in service class is accessed");
		return dao.deleteUser(p);
	}

	@Override
	public List<Person> getUsers() {
		logger.trace("getUsers() method in service class is accessed");
		return dao.getUsers();
	}

	@Override
	public List<Transaction> getTransaction() {
		logger.trace("getTransaction() method in service class is accessed");
		return dao.getTransaction();
	}

	@Override
	public Set<Transaction> getTrans(Person p) {
		logger.trace("getTrans() method in service class is accessed");
		return dao.getTrans(p);
	}

	@Override
	public Transaction makeTrans(Transaction t) {
		logger.trace("makeTrans() method in service class is accessed");
		return dao.makeTrans(t);
	}

}
