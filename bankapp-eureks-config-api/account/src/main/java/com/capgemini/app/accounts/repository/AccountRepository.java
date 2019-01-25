package com.capgemini.app.accounts.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.app.accounts.bankaccounts.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, Integer>{

}
