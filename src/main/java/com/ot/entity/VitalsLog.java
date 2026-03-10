package com.ot.entity;

import lombok.*;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "vitals_logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"hospital", "scheduledOperation"})
@ToString(exclude = {"hospital", "scheduledOperation"})
public class VitalsLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;  // 👈 NEW FIELD
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_id")
    private ScheduledOperation scheduledOperation;
    
    private LocalDateTime recordedTime;
    private String recordedBy;
    
    private Integer heartRate;
    private Integer systolicBp;
    private Integer diastolicBp;
    private Integer meanBp;
    private Integer respiratoryRate;
    private Double temperature;
    private Integer oxygenSaturation;
    private Integer etco2;
    
    private Integer painScale;
    private String consciousness;
    
    private String sedationScore;
    private String additionalNotes;
    
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}