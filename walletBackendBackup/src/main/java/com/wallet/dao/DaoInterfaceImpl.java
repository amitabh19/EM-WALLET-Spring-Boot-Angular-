package com.wallet.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.wallet.entities.Person;
import com.wallet.entities.Transaction;
import com.wallet.repositories.TransactionRepository;
import com.wallet.repositories.UserRepository;

@Repository
@AutoConfigurationPackage
public class DaoInterfaceImpl implements DaoInterface {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public TransactionRepository transactionRepository;

	@Override
	public Person addUser(Person p) {
		return userRepository.save(p);
	}

	@Override
	public Optional<Person> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public Person updateUser(Person p) {
		return userRepository.save(p);
	}

	@Override
	public void deleteUser(Person p) {
		userRepository.deleteById(p.getId());
	}

	@Override
	public List<Person> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<Transaction> getTransaction() {
		return transactionRepository.findAll();
	}

	@Override
	public Set<Transaction> getTrans(Person p) {
		return p.getTransactions();
	}
	
	@Override
	public Transaction makeTrans(Transaction t)
	{
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
