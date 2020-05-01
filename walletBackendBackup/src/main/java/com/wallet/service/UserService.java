package com.wallet.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import com.wallet.entities.Person;
import com.wallet.entities.Transaction;

@Service
@AutoConfigurationPackage
public interface UserService {
	public Person addUser(Person p);
	public Optional<Person> getUserById(Long id);
    public Person updateUser(Person p);
    public void deleteUser(Person p);
    public List<Person> getUsers();
    public List<Transaction> getTransaction();
    public Set<Transaction> getTrans(Person p);

}
