package com.wallet.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.wallet.controllers.UserController;
import com.wallet.entities.Person;
import com.wallet.entities.Transaction;
import com.wallet.repositories.TransactionRepository;
import com.wallet.repositories.UserRepository;
import com.wallet.service.UserServiceImpl;

@Repository
public class DaoInterfaceImpl implements DaoInterface {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public TransactionRepository transactionRepository;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Override
	public Person addUser(Person p) {
		logger.trace("addUser() method in dao impl class is accessed");
		return userRepository.save(p);
	}

	@Override
	public Optional<Person> getUserById(Long id) {
		logger.trace("getUserById() method in dao impl class is accessed");
		return userRepository.findById(id);
	}

	@Override
	public Person updateUser(Person p) {
		logger.trace("updateUser() method in dao impl class is accessed");
		return userRepository.save(p);
	}

	@Override
	public boolean deleteUser(Person p) {
		logger.trace("deleteUser() method in dao impl class is accessed");
		userRepository.deleteById(p.getId());
		return true;
	}

	@Override
	public List<Person> getUsers() {
		logger.trace("getUsers() method in dao impl class is accessed");
		return userRepository.findAll();
	}

	@Override
	public List<Transaction> getTransaction() {
		logger.trace("getTransaction() method in dao impl class is accessed");
		return transactionRepository.findAll();
	}

	@Override
	public Set<Transaction> getTrans(Person p) {
		logger.trace("getTrans() method in dao impl class is accessed");
		return p.getTransactions();
	}
	
	@Override
	public Transaction makeTrans(Transaction t)
	{
		logger.trace("makeTrans() method in service class is accessed");
		LocalDateTime d = LocalDateTime.now();
		if(t.gettType().equals("debit"))
		{
		Person p = userRepository.getOne(t.getSenderId());
		Person p1 = userRepository.getOne(t.getRecieverId());
		p.setBalance(p.getBalance() - t.getAmount());
		System.out.println(t.getAmount());
		p1.setBalance(p1.getBalance() + t.getAmount());
		t.settTime(d);
		t.setTxnId("TX" + stringCleaner(d.toString()));
		t.setUpdatedPersonBal(p.getBalance());
		p.addTransaction(t);
		
		userRepository.save(p);
		t.settType("credit");
		t.setUpdatedPersonBal(p1.getBalance());		
		p1.addTransaction(t);
		userRepository.save(p1);
		return t;
		}
		else if(t.gettType().equals("self withdraw"))
		{
			Person p = userRepository.getOne(t.getSenderId());
			p.setBalance(p.getBalance() - t.getAmount());
			t.settTime(d);
			t.setTxnId("TX" + stringCleaner(d.toString()));
			t.setUpdatedPersonBal(p.getBalance());
			p.addTransaction(t);
			userRepository.save(p);
			return t;
			
		}
		else if(t.gettType().equals("self deposit"))
		{
			Person p = userRepository.getOne(t.getSenderId());
			p.setBalance(p.getBalance() + t.getAmount());
			t.settTime(d);
			t.setTxnId("TX" + stringCleaner(d.toString()));
			t.setUpdatedPersonBal(p.getBalance());
			p.addTransaction(t);
			userRepository.save(p);
			return t;
		}
		return null;

	}
	public String stringCleaner(String d)
	{
		String temp_d2 = d.replace(":", "");
		String temp_d3 = temp_d2.replace("-", "");
		String temp_d4 = temp_d3.replace(".", "");
		return temp_d4;
		
	}

}
