package com.ot.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ot.dto.request.AdminCreateRequest;
import com.ot.dto.request.SuperAdminRegisterRequest;
import com.ot.dto.response.AdminResponse;
import com.ot.dto.response.SuperAdminResponse;
import com.ot.entity.Hospital;
import com.ot.entity.User;
import com.ot.enums.RoleType;
import com.ot.exception.BadRequestException;
import com.ot.exception.ForbiddenException;
import com.ot.exception.ResourceNotFoundException;
import com.ot.exception.UnauthorizedException;
import com.ot.mapper.AdminMapper;
import com.ot.mapper.SuperAdminMapper;
import com.ot.repository.HospitalRepository;
import com.ot.repository.UserRepository;
import com.ot.security.CustomUserDetails;
import com.ot.service.SuperAdminService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuperAdminServiceImpl implements SuperAdminService {
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HospitalRepository hospitalRepository;
    
	public User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("No user is logged in");
        }
        CustomUserDetails cud = (CustomUserDetails) auth.getPrincipal(); 
        return cud.getUser(); 
    }
	
	//-------------------------------------Register Super_Admin-------------------------------//
    @Override
    public SuperAdminResponse createSuperAdmin(SuperAdminRegisterRequest request) {
        log.info("Creating Super Admin with email: {}", request.getEmail());
        
        // Check if Super Admin already exists (using role)
        if (userRepository.existsByRole(RoleType.SUPER_ADMIN)) {
            throw new BadRequestException("Super Admin already exists");
        }
        
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already registered");
        }
        
        // Create Super Admin
        User superAdmin = User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RoleType.SUPER_ADMIN)
                .isActive(true)                    // 👈 Simple boolean
                .hospital(null)
                .createdBy("SYSTEM")
                .build();
        
        // Add default permission
        superAdmin.getPermissions().add("ALL_ACCESS");
        
        User savedUser = userRepository.save(superAdmin);
        log.info("Super Admin created with ID: {}", savedUser.getId());
        
        return SuperAdminMapper.toResponse(savedUser);
    }
    
//-------------------------------------Register/Create Admin-------------------------------//
    
    @Override
    public AdminResponse createAdmin(AdminCreateRequest request) {
    	
    	//Check if the User is SuperAdmin
    	User user = currentUser();
    	
    	if(user.getRole()!=RoleType.SUPER_ADMIN) {
    		throw new UnauthorizedException("You're not Allowed To Create Admin!");
    	}
    	
    	//Check Email Exists or not
    	if(userRepository.existsByEmail(request.getEmail())) {
    		throw new BadRequestException("Email Already Registered!");
    	}
    	
    	//Creating Admin
    	User admin = User.builder()
    			.userName(request.getUserName())
    			.email(request.getEmail())
    			.password(passwordEncoder.encode(request.getPassword()))
    			.role(RoleType.ADMIN)
    			.isActive(true)
                .hospital(null)   // mapping later
                .createdBy(user.getRole().name())
                .createdAt(LocalDateTime.now())
                .build();
    	
    	admin.getPermissions().add("MANAGE_OT");
    	
    	User saved = userRepository.save(admin);

    	return AdminMapper.toResponse(saved);
    }

//-------------------------------------Get Admin By ID--------------------------------//
    @Override
    public AdminResponse getAdmin(Long id) {
    	
    	//Check if the User is SuperAdmin
    	User user = currentUser();
    	
    	//Check If user is Super Admin
    	if(user.getRole()!=RoleType.SUPER_ADMIN) {
    		throw new UnauthorizedException("You're not Allowed To Acces This Resource!");
    	}
    	
    	User admin = userRepository.findById(id)
    			.orElseThrow(()-> new ResourceNotFoundException("Admin not found with id"+ id));
    	
    	if(admin.getRole()!=RoleType.ADMIN) {
    		throw new BadRequestException("User is not an admin");
    	}
    	
    	return AdminMapper.toResponse(admin);
    	
    }
 
//-------------------------------------Get All Admins--------------------------------//
    @Override
    public List<AdminResponse> getAllAdmins(){
    	
    	//Check if the User is SuperAdmin
    	User user = currentUser();
    	
    	//Check If user is Super Admin
    	if(user.getRole()!=RoleType.SUPER_ADMIN) {
    		throw new UnauthorizedException("You're not Allowed To Acces This Resource!");
    	}
    	
    	List<User> admins = userRepository.findByRole(RoleType.ADMIN);
    	
    	return admins.stream()
    			.map(AdminMapper::toResponse)
    			.toList();
    }
    
//-------------------------------------Map ADMIN to Hospital--------------------------------//
    @Override
    public String mapHospitalAdmin(Long hospitalId, Long adminId) {
    	
    	//Check if the User is SuperAdmin
    	User user = currentUser();
    	
    	//Check If user is Super Admin
    	if(user.getRole()!=RoleType.SUPER_ADMIN) {
    		throw new UnauthorizedException("You're not Allowed To Acces This Resource!");
    	}
    	
    	//Find Hospital
    	Hospital hospital = hospitalRepository.findById(hospitalId)
    			.orElseThrow(()-> new ResourceNotFoundException("Hospital Not Found!"));
    	
    	//Find Admin
    	User admin = userRepository.findById(adminId)
    			.orElseThrow(()-> new ResourceNotFoundException("Admin Not Found!"));
    	
    	//Check if User is Admin
    	if(admin.getRole()!=RoleType.ADMIN) {
    		throw new ForbiddenException("User is Not Admin");
    	}
    	
    	//Map Hospital to Admin
    	admin.setHospital(hospital);
    	
    	userRepository.save(admin);
    	
    	return "Admin Mapped SuccessFully";
    }
    
    
//-------------------------------------Update Hospital Admin Mapping--------------------------------//
    @Transactional
    @Override
    public String updateHospitalAdminMapping(Long hospitalId, Long adminId) {

        User user = currentUser();

        if (user.getRole() != RoleType.SUPER_ADMIN) {
            throw new ForbiddenException("Only SUPER_ADMIN can update admin mapping");
        }

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found"));

        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        if (admin.getRole() != RoleType.ADMIN) {
            throw new BadRequestException("User is not a hospital admin");
        }

        admin.setHospital(hospital);

        userRepository.save(admin);

        return "Admin hospital mapping updated successfully";
    }
    
    
//-------------------------------------Get Hospital Admin Mapping --------------------------------//
    @Override
    public List<AdminResponse> getHospitalAdmins(Long hospitalId) {

        User user = currentUser();

        if (user.getRole() != RoleType.SUPER_ADMIN) {
            throw new ForbiddenException("Only SUPER_ADMIN can access this resource");
        }

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found"));

        List<User> admins = userRepository
                .findByHospitalIdAndRole(hospital.getId(), RoleType.ADMIN);

        return admins.stream()
                .map(AdminMapper::toResponse)
                .toList();
    }

}
