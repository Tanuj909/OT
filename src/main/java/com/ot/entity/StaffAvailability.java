package com.ot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ot.enums.StaffAvailabilityStatus;

@Entity
@Table(name = "staff_availability")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private User staff;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

//    private String status; // AVAILABLE / UNAVAILABLE / EXTRA_SHIFT
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffAvailabilityStatus status;
}