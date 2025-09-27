package com.example.financemanager.entities;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String contactInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advisor_id", nullable = false)
    private FinancialAdvisor advisor;

    // Client has a one-to-one relationship with Portfolio (mapped in Portfolio)
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Portfolio portfolio;

    protected Client() {}

    // Full-arg constructor (includes id)
    public Client(Long id, String name, String contactInfo, FinancialAdvisor advisor, Portfolio portfolio) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.advisor = advisor;
        this.portfolio = portfolio;
    }

    // Convenience constructor
    public Client(String name, String contactInfo, FinancialAdvisor advisor) {
        this(null, name, contactInfo, advisor, null);
    }

    // Getters (no setId)
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public FinancialAdvisor getAdvisor() { return advisor; }
    public void setAdvisor(FinancialAdvisor advisor) { this.advisor = advisor; }

    public Portfolio getPortfolio() { return portfolio; }
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
        if (portfolio != null && portfolio.getClient() != this) {
            portfolio.setClient(this);
        }
    }
}
