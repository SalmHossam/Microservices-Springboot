package com.example.accounts_microservices.repository;

import com.example.accounts_microservices.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByCustomerId(Long customerId);
    Optional<Accounts> findByAccountNumber(Long accountNumber);
    void deleteByCustomerId(Long customerId);
    void deleteByAccountNumber(Long accountNumber);
}
