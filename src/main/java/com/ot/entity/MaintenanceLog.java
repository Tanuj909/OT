package com.ot.entity;

import lombok.*;
import java.time.LocalDateTime;

import com.ot.enums.MaintenanceStatus;
import com.ot.enums.MaintenanceType;

import jakarta.persistence.*;

@Entity
@Table(name = "maintenance_logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"hospital", "equipment"})
@ToString(exclude = {"hospital", "equipment"})
public class MaintenanceLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;  // 👈 NEW FIELD
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
    
    @Enumerated(EnumType.STRING)
    private MaintenanceType type;
    
    private LocalDateTime maintenanceDate;
    private LocalDateTime completionDate;
    
    private String performedBy;
    private String description;
    private String findings;
    private String recommendations;
    
    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status;
    
    private LocalDateTime nextDueDate;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    
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