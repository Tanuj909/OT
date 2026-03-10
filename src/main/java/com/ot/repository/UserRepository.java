package com.ot.repository;

import com.ot.entity.User;
import com.ot.enums.RoleType;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    boolean existsByEmail(String email);
    
    boolean existsByRole(RoleType role);  // Check by role instead of userType
    
    List<User> findByRole(RoleType role);
    
    Optional<User> findByEmail(String email);
    
	List<User> findByHospitalIdAndRole(Long hospitalId, RoleType role);
	
	List<User> findByHospitalId(Long hospitalId);
	
	List<User> findByHospitalIdAndRoleNotIn(Long hospitalId, List<RoleType> roles);
}