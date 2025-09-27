package com.example.financemanager.services;

import com.example.financemanager.entities.FinancialAdvisor;
import com.example.financemanager.repositories.FinancialAdvisorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialAdvisorService {
    private final FinancialAdvisorRepository repository;

    public FinancialAdvisorService(FinancialAdvisorRepository repository) {
        this.repository = repository;
    }

    public List<FinancialAdvisor> findAll() {
        return repository.findAll();
    }

    public Optional<FinancialAdvisor> findById(Long id) {
        return repository.findById(id);
    }

    public FinancialAdvisor save(FinancialAdvisor advisor) {
        return repository.save(advisor);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
