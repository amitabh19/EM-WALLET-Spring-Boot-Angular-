package com.wallet.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.dao.DaoInterface;
import com.wallet.dao.DaoInterfaceImpl;
import com.wallet.entities.Person;
import com.wallet.entities.Transaction;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private DaoInterface dao;
	@Override
	public Person addUser(Person p) {
		return dao.addUser(p);
	}

	@Override
	public Optional<Person> getUserById(Long id) {
		return dao.getUserById(id);
	}

	@Override
	public Person updateUser(Person p) {
		return dao.updateUser(p);
	}

	@Override
	public void deleteUser(Person p) {
		dao.deleteUser(p);
	}

	@Override
	public List<Person> getUsers() {
		return dao.getUsers();
	}

	@Override
	public List<Transaction> getTransaction() {
		return dao.getTransaction();
	}

	@Override
	public Set<Transaction> getTrans(Person p) {
		return dao.getTrans(p);
	}

}
