package com.ot.entity;

import lombok.*;
import java.time.LocalDateTime;
import java.util.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "hospitals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String hospitalCode;
    
    @Column(nullable = false)
    private String hospitalName;
    
    private String registrationNumber;
    private String taxId;
    
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    
    private String phone;
    private String email;
    private String website;
    
    private String logo;
    private Boolean isActive;
    
    @Enumerated(EnumType.STRING)
    private HospitalStatus status;
    
    @Enumerated(EnumType.STRING)
    private SubscriptionPlan subscriptionPlan;
    
    private LocalDateTime subscriptionStartDate;
    private LocalDateTime subscriptionEndDate;
    
    private Integer maxOts;
    private Integer maxUsers;
    private Integer maxOperationsPerMonth;
    
    private String databaseName;  // For separate DB per tenant
    private String schemaName;     // For schema-based multi-tenancy
    
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @Version
    private Long version;
    
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();
    
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OperationTheater> operationTheaters = new HashSet<>();
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum HospitalStatus {
        ACTIVE, INACTIVE, SUSPENDED, TRIAL, EXPIRED
    }
    
    public enum SubscriptionPlan {
        FREE, BASIC, PROFESSIONAL, ENTERPRISE, CUSTOM
    }
}
