package com.ot.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ot.dto.request.CreateStaffAvailabilityRequest;
import com.ot.dto.response.StaffAvailabilityResponse;
import com.ot.entity.StaffAvailability;
import com.ot.entity.StaffSchedule;
import com.ot.entity.User;
import com.ot.enums.RoleType;
import com.ot.enums.StaffAvailabilityStatus;
import com.ot.exception.ForbiddenException;
import com.ot.exception.ResourceNotFoundException;
import com.ot.repository.StaffAvailabilityRepository;
import com.ot.repository.StaffScheduleRepository;
import com.ot.repository.UserRepository;
import com.ot.security.CustomUserDetails;
import com.ot.service.StaffAvailabilityService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffAvailabilityServiceImpl implements StaffAvailabilityService{
	
    private final UserRepository userRepository;
    private final StaffAvailabilityRepository staffAvailabilityRepository;
    private final StaffScheduleRepository staffScheduleRepository;
	
	public User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("No user is logged in");
        }
        CustomUserDetails cud = (CustomUserDetails) auth.getPrincipal(); 
        return cud.getUser(); 
    }
	
	
	@Transactional
	@Override
	public StaffAvailabilityResponse createStaffAvailability(CreateStaffAvailabilityRequest request) {

	    User admin = currentUser();

	    if(admin.getRole() != RoleType.ADMIN){
	        throw new ForbiddenException("Only hospital admin can manage staff availability");
	    }

	    User staff = userRepository.findById(request.getStaffId())
	            .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));
	    
	    if(!staff.getHospital().getId().equals(admin.getHospital().getId())){
	        throw new ForbiddenException("Staff does not belong to your hospital");
	    }
	    
        if (request.getStartTime().isAfter(request.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

	    StaffAvailability availability = StaffAvailability.builder()
	            .staff(staff)
	            .date(request.getDate())
	            .startTime(request.getStartTime())
	            .endTime(request.getEndTime())
	            .status(request.getStatus())
	            .build();

	    StaffAvailability saved = staffAvailabilityRepository.save(availability);

	    return StaffAvailabilityResponse.builder()
	            .id(saved.getId())
	            .staffId(saved.getStaff().getId())
	            .date(saved.getDate())
	            .startTime(saved.getStartTime())
	            .endTime(saved.getEndTime())
	            .status(saved.getStatus())
	            .build();

	}
	
	
	@Override
	public List<StaffAvailabilityResponse> getStaffAvailabilityByStaff(Long staffId) {

	    User admin = currentUser();

	    if(admin.getRole() != RoleType.ADMIN){
	        throw new ForbiddenException("Only hospital admin can view staff availability");
	    }

	    User staff = userRepository.findById(staffId)
	            .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));

	    if(!staff.getHospital().getId().equals(admin.getHospital().getId())){
	        throw new ForbiddenException("Staff does not belong to your hospital");
	    }

	    List<StaffAvailability> availabilityList =
	            staffAvailabilityRepository.findByStaffId(staffId);

	    return availabilityList.stream()
	            .map(a -> StaffAvailabilityResponse.builder()
	                    .id(a.getId())
	                    .staffId(a.getStaff().getId())
	                    .date(a.getDate())
	                    .startTime(a.getStartTime())
	                    .endTime(a.getEndTime())
	                    .status(a.getStatus())
	                    .build())
	            .toList();
	}
	
	@Override
	public boolean isStaffAvailable(Long staffId, LocalDate date, LocalTime start, LocalTime end) {
		
		// Step 1: date specific availability check
		List<StaffAvailability> availabilityList = staffAvailabilityRepository.findByStaffIdAndDate(staffId, date);

		if (!availabilityList.isEmpty()) {

			for (StaffAvailability availability : availabilityList) {
				
				// Leave or unavailable
				if (availability.getStatus() == StaffAvailabilityStatus.UNAVAILABLE) {
					return false;
				}
				// Check time slot inside availability
				boolean withinSlot = !start.isBefore(availability.getStartTime())
						&& !end.isAfter(availability.getEndTime());

				if (withinSlot) {
					return true;
				}
			}

			return false;
		}

		// Step 2: fallback to weekly schedule
		DayOfWeek day = date.getDayOfWeek();

		StaffSchedule schedule = staffScheduleRepository.findByStaffIdAndDayOfWeek(staffId, day).orElse(null);

		if (schedule == null) {
			return false;
		}

		return !start.isBefore(schedule.getStartTime()) && !end.isAfter(schedule.getEndTime());
	}
	@Override
	@Transactional
	public void deleteStaffAvailability(Long availabilityId) {

	    User admin = currentUser();

	    if(admin.getRole() != RoleType.ADMIN){
	        throw new ForbiddenException("Only hospital admin can delete staff availability");
	    }

	    StaffAvailability availability = staffAvailabilityRepository.findById(availabilityId)
	            .orElseThrow(() -> new ResourceNotFoundException("Availability not found"));

	    if(!availability.getStaff().getHospital().getId()
	            .equals(admin.getHospital().getId())){
	        throw new ForbiddenException("You cannot delete this availability");
	    }

	    staffAvailabilityRepository.delete(availability);
	}

}
