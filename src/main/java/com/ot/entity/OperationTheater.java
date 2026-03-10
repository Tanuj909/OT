package com.ot.entity;

import lombok.*;
import java.time.LocalDateTime;
import java.util.*;
import com.ot.enums.TheaterStatus;
import com.ot.enums.TheaterType;
import jakarta.persistence.*;

@Entity
@Table(name = "operation_theaters")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"hospital", "equipment", "scheduledOperations", "attributes", "schedules"})
@ToString(exclude = {"hospital", "equipment", "scheduledOperations", "attributes", "schedules"})
public class OperationTheater {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;  // 👈 NEW FIELD
    
    @Column(nullable = false)
    private String theaterNumber;  // Removed unique - now unique per hospital
    
    private String name;
    private String location;
    private String building;
    private Integer floor;
    
    @Enumerated(EnumType.STRING)
    private TheaterStatus status;
    
    @Enumerated(EnumType.STRING)
    private TheaterType type;
    
    private Double length;
    private Double width;
    private Double height;
    private String dimensionUnit;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    
    @Version
    private Long version;
    
    @OneToMany(mappedBy = "operationTheater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Equipment> equipment = new HashSet<>();
    
//    @OneToMany(mappedBy = "operationTheater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<ScheduledOperation> scheduledOperations = new HashSet<>();
    
    @OneToMany(mappedBy = "operationTheater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TheaterAttribute> attributes = new HashSet<>();
    
//    @OneToMany(mappedBy = "operationTheater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<OTSchedule> schedules = new HashSet<>();
    
//    @OneToOne
//    @JoinColumn(name = "ot_room_id")
//    private OTRoom otRoom;
    
    @OneToMany(mappedBy = "operationTheater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OTRoom> rooms = new HashSet<>();
    
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