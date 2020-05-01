package com.wallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallet.entities.Person;


@Repository
public interface UserRepository extends JpaRepository<Person,Long> {

}
