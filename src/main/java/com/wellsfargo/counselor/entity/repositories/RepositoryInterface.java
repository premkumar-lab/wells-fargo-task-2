package com.example.financemanager.repositories;

import com.example.financemanager.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialAdvisorRepository extends JpaRepository<FinancialAdvisor, Long> {}
public interface ClientRepository extends JpaRepository<Client, Long> {}
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {}
public interface SecurityRepository extends JpaRepository<Security, Long> {}
