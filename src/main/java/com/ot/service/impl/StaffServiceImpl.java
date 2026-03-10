package com.ot.service.impl;

import java.time.DayOfWeek;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ot.dto.request.CreateStaffAvailabilityRequest;
import com.ot.dto.request.CreateStaffRequest;
import com.ot.dto.request.CreateStaffScheduleRequest;
import com.ot.dto.request.UpdateStaffRequest;
import com.ot.dto.request.UpdateStaffScheduleRequest;
import com.ot.dto.response.StaffResponse;
import com.ot.dto.response.StaffScheduleResponse;
import com.ot.entity.StaffAvailability;
import com.ot.entity.StaffSchedule;
import com.ot.entity.User;
import com.ot.enums.RoleType;
import com.ot.exception.BadRequestException;
import com.ot.exception.DuplicateResourceException;
import com.ot.exception.ForbiddenException;
import com.ot.exception.ResourceNotFoundException;
import com.ot.exception.UnauthorizedException;
import com.ot.mapper.StaffMapper;
import com.ot.mapper.StaffScheduleMapper;
import com.ot.repository.StaffScheduleRepository;
import com.ot.repository.UserRepository;
import com.ot.security.CustomUserDetails;
import com.ot.service.StaffService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StaffScheduleRepository staffScheduleRepository;
	
	public User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("No user is logged in");
        }
        CustomUserDetails cud = (CustomUserDetails) auth.getPrincipal(); 
        return cud.getUser(); 
    }
	
	@Override
	public StaffResponse createStaff(CreateStaffRequest request) {

	    User currentUser = currentUser();

	    if (currentUser.getRole() != RoleType.ADMIN) {
	        throw new ForbiddenException("Only Hospital Admin can create staff");
	    }

	    if (userRepository.existsByEmail(request.getEmail())) {
	        throw new DuplicateResourceException("Email already registered");
	    }

	    User staff = User.builder()
	            .userName(request.getName())
	            .email(request.getEmail())
	            .password(passwordEncoder.encode(request.getPassword()))
	            .role(request.getRole())
	            .hospital(currentUser.getHospital())
	            .isActive(true)
	            .createdBy(currentUser.getEmail())
	            .build();

	    User saved = userRepository.save(staff);

	    StaffResponse response = new StaffResponse();
	    response.setId(saved.getId());
	    response.setName(saved.getUserName());
	    response.setEmail(saved.getEmail());
	    response.setRole(saved.getRole());
	    response.setActive(saved.getIsActive());

	    return response;
	}
	
	
	@Override
	public StaffResponse updateStaff(Long staffId, UpdateStaffRequest request) {

	    User admin = currentUser();

	    if(admin.getRole() != RoleType.ADMIN){
	        throw new ForbiddenException("Only hospital admin can update staff");
	    }

	    User staff = userRepository.findById(staffId)
	            .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));

	    if(request.getName() != null){
	        staff.setUserName(request.getName());
	    }

	    if(request.getEmail() != null){
	        staff.setEmail(request.getEmail());
	    }

	    if(request.getRole() != null){
	        staff.setRole(request.getRole());
	    }

	    userRepository.save(staff);

	    return StaffMapper.toResponse(staff);
	}
	
	@Override
	public StaffResponse getStaff(Long staffId){

	    User admin = currentUser();

	    if(admin.getRole() != RoleType.ADMIN){
	        throw new ForbiddenException("Access denied");
	    }

	    User staff = userRepository.findById(staffId)
	            .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));

	    return StaffMapper.toResponse(staff);
	}
	
	
	@Override
	public List<StaffResponse> getAllStaff() {

	    User admin = currentUser();

	    if (admin.getRole() != RoleType.ADMIN) {
	        throw new ForbiddenException("Access denied");
	    }

	    List<RoleType> excludedRoles = List.of(
	            RoleType.ADMIN,
	            RoleType.SUPER_ADMIN
//	            RoleType.PATIENT
	    );

	    List<User> staffList =
	            userRepository.findByHospitalIdAndRoleNotIn(
	                    admin.getHospital().getId(),
	                    excludedRoles
	            );

	    return staffList.stream()
	            .map(StaffMapper::toResponse)
	            .toList();
	}
	
	
	@Override
	public String deactivateStaff(Long staffId){

	    User admin = currentUser();

	    if(admin.getRole() != RoleType.ADMIN){
	        throw new ForbiddenException("Access denied");
	    }

	    User staff = userRepository.findById(staffId)
	            .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));

	    staff.setIsActive(false);

	    userRepository.save(staff);

	    return "Staff deactivated successfully";
	}
	
	@Override
	public String activateStaff(Long staffId){

	    User admin = currentUser();

	    if(admin.getRole() != RoleType.ADMIN){
	        throw new ForbiddenException("Access denied");
	    }

	    User staff = userRepository.findById(staffId)
	            .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));

	    staff.setIsActive(true);

	    userRepository.save(staff);

	    return "Staff deactivated successfully";
	}

	
	@Transactional
	@Override
	public String createStaffSchedule(CreateStaffScheduleRequest request) {

	    User admin = currentUser();

	    if(admin.getRole() != RoleType.ADMIN){
	        throw new ForbiddenException("Only hospital admin can manage schedules");
	    }

	    User staff = userRepository.findById(request.getStaffId())
	            .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));
	    
	    if(!staff.getHospital().getId().equals(admin.getHospital().getId())){
	        throw new UnauthorizedException("You are not allowed to manage this staff");
	    }
	    
	    if(request.getEndTime().isBefore(request.getStartTime())){
	        throw new BadRequestException("End time must be after start time");
	    }

	    for (DayOfWeek day : request.getDays()) {
	    	
	    	  boolean exists = staffScheduleRepository
	                  .existsByStaffAndDayOfWeek(staff, day);
	    	  
	          if(exists){
	              throw new BadRequestException("Schedule already exists for " + day);
	          }

	        StaffSchedule schedule = StaffSchedule.builder()
	                .staff(staff)
	                .dayOfWeek(day)
	                .startTime(request.getStartTime())
	                .endTime(request.getEndTime())
	                .build();

	        staffScheduleRepository.save(schedule);
	    }

	    return "Staff schedule created successfully";
	}
	
	
	@Transactional
	@Override
	public String updateStaffSchedule(Long scheduleId, UpdateStaffScheduleRequest request) {
		
	    User admin = currentUser();
	    
	    if(admin.getRole() != RoleType.ADMIN){
	        throw new ForbiddenException("Only hospital admin can update schedule");
	    }
	    
	    StaffSchedule schedule = staffScheduleRepository.findById(scheduleId)
	            .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
	    
	    //Find Staff (If Belongs to the Same Hospital as Admin's)
	    User staff = schedule.getStaff();
	    
	    //Find Staff Hospital Id
	    if(!staff.getHospital().getId().equals(admin.getHospital().getId())) {
	    	throw new UnauthorizedException("You are not Allowed");
	    }
	    
	    if(request.getStartTime() != null && request.getEndTime() != null){
	        if(request.getEndTime().isBefore(request.getStartTime())){
	            throw new BadRequestException("End time must be after start time");
	        }
	    }

	    if(request.getDayOfWeek() != null){
	        schedule.setDayOfWeek(request.getDayOfWeek());
	    }

	    if(request.getStartTime() != null){
	        schedule.setStartTime(request.getStartTime());
	    }

	    if(request.getEndTime() != null){
	        schedule.setEndTime(request.getEndTime());
	    }
	    
	    staffScheduleRepository.save(schedule);

	    return "Staff schedule updated successfully";
	}
	
	@Override
	public List<StaffScheduleResponse> getStaffSchedule(Long staffId){

	    User admin = currentUser();

	    if(admin.getRole() != RoleType.ADMIN){
	        throw new ForbiddenException("Access denied");
	    }

	    return staffScheduleRepository.findByStaffId(staffId)
	    		.stream()
	    		.map(StaffScheduleMapper::toResponse)
	    		.toList();
	}
	
	@Transactional
	@Override
	public String deleteStaffSchedule(Long scheduleId){

	    User admin = currentUser();

	    if(admin.getRole() != RoleType.ADMIN){
	        throw new ForbiddenException("Access denied");
	    }

	    StaffSchedule schedule = staffScheduleRepository.findById(scheduleId)
	            .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
	    
	    //Find Staff (If Belongs to the Same Hospital as Admin's)
	    User staff = schedule.getStaff();
	    
	    //Find Staff Hospital Id
	    if(!staff.getHospital().getId().equals(admin.getHospital().getId())) {
	    	throw new UnauthorizedException("You are not Allowed");
	    }

	    staffScheduleRepository.delete(schedule);

	    return "Staff schedule deleted successfully";
	}
	
}
