package com.ot.entity;

import lombok.*;
import java.time.LocalDateTime;
import java.util.*;
import com.ot.enums.RoleType;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    private String userName;
    private String password;
    
    @Enumerated(EnumType.STRING)
    private RoleType role;           // SUPER_ADMIN, HOSPITAL_ADMIN, etc.
    
    private Boolean isActive;        // 👈 Simple boolean for status
    
    @ElementCollection
    @CollectionTable(name = "user_permissions", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "permission")
    @Builder.Default
    private Set<String> permissions = new HashSet<>();
    
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
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