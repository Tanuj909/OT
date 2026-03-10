package com.ot.service;

import java.util.List;

import com.ot.dto.request.CreateHospitalRequest;
import com.ot.dto.request.UpdateHospitalRequest;
import com.ot.dto.response.HospitalResponse;

public interface OtHospitalService {

    HospitalResponse createHospital(CreateHospitalRequest request);

    HospitalResponse updateHospital(Long hospitalId, UpdateHospitalRequest request);

    HospitalResponse getHospital(Long hospitalId);

	List<HospitalResponse> getAllHospitals();
}
