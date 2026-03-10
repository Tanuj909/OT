package com.ot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ot.dto.request.AdminCreateRequest;
import com.ot.dto.request.SuperAdminRegisterRequest;
import com.ot.dto.response.AdminResponse;
import com.ot.dto.response.ApiResponse;
import com.ot.dto.response.SuperAdminResponse;
import com.ot.service.SuperAdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/super-admin")
@RequiredArgsConstructor
public class SuperAdminController {
	
	private final SuperAdminService superAdminService;
	
//---------------------------------Super_Admin Registration---------------------------------//
	@PostMapping("/register")
	public ApiResponse<SuperAdminResponse> registerSuperAdmin(@Valid @RequestBody SuperAdminRegisterRequest request,
			HttpServletRequest httpRequest) {

		SuperAdminResponse response = superAdminService.createSuperAdmin(request);

		return ApiResponse.success("Super Admin created successfully", response, httpRequest.getRequestURI());
	}

//---------------------------------Create ADMIN---------------------------------//
	@PostMapping("/create-admin")
	public ApiResponse<AdminResponse> createAdmin(@Valid @RequestBody AdminCreateRequest request,
			HttpServletRequest httpRequest) {

		AdminResponse response = superAdminService.createAdmin(request);

		return ApiResponse.success("Admin created successfully", response, httpRequest.getRequestURI());
	}

//Update ADMINS

//---------------------------------Get ADMIN By Id---------------------------------//
	@GetMapping("/admin/{id}")
	public ApiResponse<AdminResponse> getAdmin(@PathVariable Long id) {

		AdminResponse response = superAdminService.getAdmin(id);

		return ApiResponse.success("Admin Fetched!", response);
	}

//---------------------------------Get All ADMINS---------------------------------//
	@GetMapping("/admin-all")
	public ApiResponse<List<AdminResponse>> findAllAdmins() {
		List<AdminResponse> admins = superAdminService.getAllAdmins();

		return ApiResponse.success("Admins fetched successfully", admins);
	}
    
//---------------------------------Hospital <-> ADMIN Mapping---------------------------------//
	@PostMapping("/map-admin-hospital")
	public ApiResponse<String> mapHospitalAdmin(@RequestParam Long hospitalId, @RequestParam Long adminId){
		String response = superAdminService.mapHospitalAdmin(hospitalId, adminId);
		
        return ApiResponse.success(
                response,
                null
        );
	}
	
//--------------------------------
	@PutMapping("/update-admin-hospital")
	public ApiResponse<String> updateHospitalAdminMapping(
	        @RequestParam Long hospitalId,
	        @RequestParam Long adminId,
	        HttpServletRequest request) {

	    String response = superAdminService.updateHospitalAdminMapping(hospitalId, adminId);

	    return ApiResponse.success(
	            response,
	            null,
	            request.getRequestURI()
	    );
	}
	
	@GetMapping("/hospital-admins/{hospitalId}")
	public ApiResponse<List<AdminResponse>> getHospitalAdmins(
	        @PathVariable Long hospitalId,
	        HttpServletRequest request) {

	    List<AdminResponse> admins = superAdminService.getHospitalAdmins(hospitalId);

	    return ApiResponse.success(
	            "Hospital admins fetched successfully",
	            admins,
	            request.getRequestURI()
	    );
	}
}
