package com.ot.entity;

import lombok.*;
import java.time.LocalDateTime;
import com.ot.enums.ActionType;
import com.ot.enums.AuditStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "audit_trail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditTrail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;  // 👈 NEW FIELD (nullable for SUPER_ADMIN actions)
    
    private LocalDateTime timestamp;
    private String userId;
    private String userName;
    private String userRole;
    private String ipAddress;
    
    @Enumerated(EnumType.STRING)
    private ActionType action;
    
    private String entityName;
    private String entityId;
    
    @Column(length = 5000)
    private String oldValue;
    
    @Column(length = 5000)
    private String newValue;
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    private AuditStatus status;
    
    private String errorMessage;
    
    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }
}