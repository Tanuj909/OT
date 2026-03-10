package com.ot.entity;

import lombok.*;
import java.time.LocalDateTime;
import java.util.*;
import com.ot.enums.EquipmentCategory;
import com.ot.enums.EquipmentStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "equipment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"hospital", "operationTheater", "maintenanceLogs", "attributes", "usedInOperations"})
@ToString(exclude = {"hospital", "operationTheater", "maintenanceLogs", "attributes", "usedInOperations"})
public class Equipment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;  // 👈 NEW FIELD
    
    @Column(nullable = false)
    private String name;
    
    private String model;
    private String manufacturer;
    private String serialNumber;
    
    @Column(unique = true)
    private String assetCode;  // Can be unique across system
    
    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;
    
    @Enumerated(EnumType.STRING)
    private EquipmentCategory category;
    
    private LocalDateTime purchaseDate;
    private LocalDateTime lastMaintenanceDate;
    private LocalDateTime nextMaintenanceDate;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    
    @Version
    private Long version;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_theater_id")
    private OperationTheater operationTheater;
    
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MaintenanceLog> maintenanceLogs = new HashSet<>();
    
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<EquipmentAttribute> attributes = new HashSet<>();
    
    @OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY)
    private Set<UsedEquipment> usedInOperations = new HashSet<>();
    
    @ElementCollection
    @CollectionTable(name = "equipment_capabilities", 
                     joinColumns = @JoinColumn(name = "equipment_id"))
    @Column(name = "capability")
    private Set<String> capabilities = new HashSet<>();
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}