package com.jhcm.appdirect.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhcm.appdirect.backend.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
