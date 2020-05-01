package com.wallet.dao;

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

}
