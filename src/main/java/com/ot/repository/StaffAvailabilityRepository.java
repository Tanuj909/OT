package com.ot.repository;

import com.ot.entity.StaffAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StaffAvailabilityRepository
        extends JpaRepository<StaffAvailability, Long> {

    List<StaffAvailability> findByStaffId(Long staffId);
    
    List<StaffAvailability> findByStaffIdAndDate(Long staffId, LocalDate date);

}