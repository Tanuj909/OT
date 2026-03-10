package com.ot.entity;

import lombok.*;
import java.time.LocalDateTime;

import com.ot.enums.AttributeType;

import jakarta.persistence.*;

@Entity
@Table(name = "equipment_attributes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"hospital", "equipment"})
@ToString(exclude = {"hospital", "equipment"})
public class EquipmentAttribute {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;  // 👈 NEW FIELD
    
    @Column(nullable = false)
    private String attributeName;
    
    @Column(nullable = false)
    private String attributeValue;
    
    @Enumerated(EnumType.STRING)
    private AttributeType attributeType;
    
    private boolean isRequired;
    private boolean isSystem;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
    
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