package com.example.financemanager.controllers;

import com.example.financemanager.entities.FinancialAdvisor;
import com.example.financemanager.services.FinancialAdvisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advisors")
public class FinancialAdvisorController {
    private final FinancialAdvisorService service;

    public FinancialAdvisorController(FinancialAdvisorService service) {
        this.service = service;
    }

    @GetMapping
    public List<FinancialAdvisor> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialAdvisor> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FinancialAdvisor create(@RequestBody FinancialAdvisor advisor) {
        return service.save(advisor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialAdvisor> update(@PathVariable Long id, @RequestBody FinancialAdvisor updatedAdvisor) {
        return service.findById(id)
                .map(existing -> {
                    existing.setName(updatedAdvisor.getName());
                    existing.setEmail(updatedAdvisor.getEmail());
                    existing.setPhone(updatedAdvisor.getPhone());
                    return ResponseEntity.ok(service.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
