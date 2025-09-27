package com.example.financemanager.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "portfolios")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Portfolio owns the 1:1 relationship with Client (has client_id FK)
    @OneToOne
    @JoinColumn(name = "client_id", nullable = false, unique = true)
    private Client client;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Security> securities = new HashSet<>();

    protected Portfolio() {}

    // Full-arg constructor (initializes all instance vars including id)
    public Portfolio(Long id, Client client, Set<Security> securities) {
        this.id = id;
        this.client = client;
        this.securities = securities != null ? securities : new HashSet<>();
    }

    // Convenience constructor (without id)
    public Portfolio(Client client) {
        this(null, client, null);
    }

    public Long getId() { return id; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Set<Security> getSecurities() { return securities; }
    public void setSecurities(Set<Security> securities) { this.securities = securities != null ? securities : new HashSet<>(); }

    // helper methods
    public void addSecurity(Security security) {
        securities.add(security);
        security.setPortfolio(this);
    }

    public void removeSecurity(Security security) {
        securities.remove(security);
        security.setPortfolio(null);
    }
}
