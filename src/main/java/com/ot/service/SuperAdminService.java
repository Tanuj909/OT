package com.ot.service;

import java.util.List;

import com.ot.dto.request.AdminCreateRequest;
import com.ot.dto.request.SuperAdminRegisterRequest;
import com.ot.dto.response.AdminResponse;
import com.ot.dto.response.SuperAdminResponse;

public interface SuperAdminService {

	SuperAdminResponse createSuperAdmin(SuperAdminRegisterRequest request);

	AdminResponse createAdmin(AdminCreateRequest request);

	AdminResponse getAdmin(Long id);

	List<AdminResponse> getAllAdmins();

	String mapHospitalAdmin(Long hospitalId, Long adminId);

	List<AdminResponse> getHospitalAdmins(Long hospitalId);

	String updateHospitalAdminMapping(Long hospitalId, Long adminId);

}
