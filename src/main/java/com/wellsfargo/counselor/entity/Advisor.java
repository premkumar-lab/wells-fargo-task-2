package com.example.financemanager.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "financial_advisors")
public class FinancialAdvisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @OneToMany(mappedBy = "advisor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Client> clients = new HashSet<>();

    // JPA requires a no-arg constructor
    protected FinancialAdvisor() {}

    // Full-arg constructor (initializes all instance vars including id)
    public FinancialAdvisor(Long id, String name, String email, String phone, Set<Client> clients) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.clients = clients != null ? clients : new HashSet<>();
    }

    // Convenience constructor (without id)
    public FinancialAdvisor(String name, String email, String phone) {
        this(null, name, email, phone, null);
    }

    // Getters (no setId)
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Set<Client> getClients() { return clients; }
    public void setClients(Set<Client> clients) {
        this.clients = clients != null ? clients : new HashSet<>();
    }

    // Helper methods
    public void addClient(Client client) {
        clients.add(client);
        client.setAdvisor(this);
    }

    public void removeClient(Client client) {
        clients.remove(client);
        client.setAdvisor(null);
    }
}
