package com.ot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ot.dto.request.CreateHospitalRequest;
import com.ot.dto.request.UpdateHospitalRequest;
import com.ot.dto.response.HospitalResponse;
import com.ot.entity.Hospital;
import com.ot.exception.ResourceNotFoundException;
import com.ot.repository.HospitalRepository;
import com.ot.security.SecurityUtils;
import com.ot.service.OtHospitalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtHospitalServiceImpl implements OtHospitalService {
	
    private final HospitalRepository hospitalRepository;
	
//--------------------------------------Create Hospital--------------------------------------//
	 @Override
	    public HospitalResponse createHospital(CreateHospitalRequest request) {

	        String createdBy = SecurityUtils.getCurrentUserEmail();

	        Hospital hospital = Hospital.builder()
	                .hospitalName(request.getName())
	                .hospitalCode("HOSP-" + System.currentTimeMillis())
	                .address(request.getAddress())
	                .city(request.getCity())
	                .isActive(true)
	                .createdBy(createdBy)
	                .build();

	        Hospital saved = hospitalRepository.save(hospital);

	        return mapToResponse(saved);
	    }

	
//--------------------------------------Update Hospital--------------------------------------//
	    @Override
	    public HospitalResponse updateHospital(Long hospitalId, UpdateHospitalRequest request) {

	        Hospital hospital = hospitalRepository.findById(hospitalId)
	                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found"));

	        if(request.getName() != null){
	            hospital.setHospitalName(request.getName());
	        }

	        if(request.getAddress() != null){
	            hospital.setAddress(request.getAddress());
	        }

	        if(request.getCity() != null){
	            hospital.setCity(request.getCity());
	        }

	        if(request.getIsActive() != null){
	            hospital.setIsActive(request.getIsActive());
	        }

	        hospitalRepository.save(hospital);

	        return mapToResponse(hospital);
	    }
	
//--------------------------------------Get Hospital By Id--------------------------------------//
	    @Override
	    public HospitalResponse getHospital(Long hospitalId) {

	        Hospital hospital = hospitalRepository.findById(hospitalId)
	                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found"));

	        return mapToResponse(hospital);
	    }

	    private HospitalResponse mapToResponse(Hospital hospital){

	        HospitalResponse response = new HospitalResponse();

	        response.setId(hospital.getId());
	        response.setName(hospital.getHospitalName());
	        response.setAddress(hospital.getAddress());
	        response.setCity(hospital.getCity());
	        response.setActive(hospital.getIsActive());

	        return response;
	    }
	    
//	    Get All Hospitals
	    @Override
	    public List<HospitalResponse> getAllHospitals() {

	        return hospitalRepository.findAll()
	                .stream()
	                .map(this::mapToResponse)
	                .toList();
	    }
	
//  Map ADMIN to Hospital
	
//	Map staff to hospital
	
//	Update ADMIN <-> Hospital Mapping
	
//	Update Staff<-> Hospital Mapping
	
}
