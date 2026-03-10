package com.ot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ot.dto.request.CreateHospitalRequest;
import com.ot.dto.request.UpdateHospitalRequest;
import com.ot.dto.response.ApiResponse;
import com.ot.dto.response.HospitalResponse;
import com.ot.service.OtHospitalService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/super-admin/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final OtHospitalService hospitalService;

    @PostMapping
    public ApiResponse<HospitalResponse> createHospital(
            @Valid @RequestBody CreateHospitalRequest request,
            HttpServletRequest httpRequest){

        HospitalResponse response = hospitalService.createHospital(request);

        return ApiResponse.success(
                "Hospital created successfully",
                response,
                httpRequest.getRequestURI()
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<HospitalResponse> updateHospital(
            @PathVariable Long id,
            @RequestBody UpdateHospitalRequest request,
            HttpServletRequest httpRequest){

        HospitalResponse response = hospitalService.updateHospital(id, request);

        return ApiResponse.success(
                "Hospital updated successfully",
                response,
                httpRequest.getRequestURI()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<HospitalResponse> getHospital(
            @PathVariable Long id,
            HttpServletRequest httpRequest){

        HospitalResponse response = hospitalService.getHospital(id);

        return ApiResponse.success(
                "Hospital fetched successfully",
                response,
                httpRequest.getRequestURI()
        );
    }
    
    @GetMapping
    public ApiResponse<List<HospitalResponse>> getAllHospitals(
            HttpServletRequest request) {

        List<HospitalResponse> hospitals = hospitalService.getAllHospitals();

        return ApiResponse.success(
                "Hospitals fetched successfully",
                hospitals,
                request.getRequestURI()
        );
    }
}
