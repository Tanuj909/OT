package com.ot.repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ot.entity.StaffSchedule;
import com.ot.entity.User;

public interface StaffScheduleRepository
extends JpaRepository<StaffSchedule, Long> {
	
	List<StaffSchedule> findByStaffId(Long staffId);

	boolean existsByStaffAndDayOfWeek(User staff, DayOfWeek day);
	
	Optional<StaffSchedule> findByStaffIdAndDayOfWeek(Long staffId, DayOfWeek dayOfWeek);
}