package com.ot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ot.dto.request.CreateStaffRequest;
import com.ot.dto.request.CreateStaffScheduleRequest;
import com.ot.dto.request.UpdateStaffRequest;
import com.ot.dto.request.UpdateStaffScheduleRequest;
import com.ot.dto.response.ApiResponse;
import com.ot.dto.response.StaffResponse;
import com.ot.dto.response.StaffScheduleResponse;
import com.ot.entity.StaffSchedule;
import com.ot.service.StaffService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/staff")
@RequiredArgsConstructor
public class StaffContoller {
	
	private final StaffService staffService;
	
    @PostMapping
    public ApiResponse<StaffResponse> createStaff(
            @RequestBody CreateStaffRequest request,
            HttpServletRequest httpRequest) {

        StaffResponse response = staffService.createStaff(request);

        return ApiResponse.success(
                "Staff created successfully",
                response,
                httpRequest.getRequestURI()
        );
    }
    
    @PutMapping("/{staffId}")
    public ApiResponse<StaffResponse> updateStaff(
            @PathVariable Long staffId,
            @RequestBody UpdateStaffRequest request,
            HttpServletRequest httpRequest){

        StaffResponse response = staffService.updateStaff(staffId, request);

        return ApiResponse.success(
                "Staff updated successfully",
                response,
                httpRequest.getRequestURI()
        );
    }
    
    @GetMapping("/{staffId}")
    public ApiResponse<StaffResponse> getStaff(
            @PathVariable Long staffId,
            HttpServletRequest request){

        StaffResponse response = staffService.getStaff(staffId);

        return ApiResponse.success(
                "Staff fetched successfully",
                response,
                request.getRequestURI()
        );
    }
    
    @GetMapping
    public ApiResponse<List<StaffResponse>> getAllStaff(
            HttpServletRequest request){

        List<StaffResponse> staff = staffService.getAllStaff();

        return ApiResponse.success(
                "Staff list fetched successfully",
                staff,
                request.getRequestURI()
        );
    }
    
    @PatchMapping("/{staffId}/deactivate")
    public ApiResponse<String> deactivateStaff(
            @PathVariable Long staffId,
            HttpServletRequest request){

        String response = staffService.deactivateStaff(staffId);

        return ApiResponse.success(
                response,
                null,
                request.getRequestURI()
        );
    }
    
    @PatchMapping("/{staffId}/activate")
    public ApiResponse<String> activateStaff(
            @PathVariable Long staffId,
            HttpServletRequest request){

        String response = staffService.activateStaff(staffId);

        return ApiResponse.success(
                response,
                null,
                request.getRequestURI()
        );
    }

    
    @PostMapping("/staff-schedule")
    public ApiResponse<String> createStaffSchedule(
            @RequestBody CreateStaffScheduleRequest request,
            HttpServletRequest httpRequest){

        String response = staffService.createStaffSchedule(request);

        return ApiResponse.success(
                response,
                null,
                httpRequest.getRequestURI()
        );
    }
    
    @PutMapping("/staff-schedule/{scheduleId}")
    public ApiResponse<String> updateStaffSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateStaffScheduleRequest request,
            HttpServletRequest httpRequest){

        String response = staffService.updateStaffSchedule(scheduleId, request);

        return ApiResponse.success(
                response,
                null,
                httpRequest.getRequestURI()
        );
    }
    
    @GetMapping("/get-staff-schedule/{staffId}")
    public ApiResponse<List<StaffScheduleResponse>> getStaffSchedule(
            @PathVariable Long staffId,
            HttpServletRequest request){

        List<StaffScheduleResponse> schedule = staffService.getStaffSchedule(staffId);

        return ApiResponse.success(
                "Staff schedule fetched successfully",
                schedule,
                request.getRequestURI()
        );
    }
    
    @DeleteMapping("/delete-schedule/{scheduleId}")
    public ApiResponse<String> deleteStaffSchedule(
            @PathVariable Long scheduleId,
            HttpServletRequest request){

        String response = staffService.deleteStaffSchedule(scheduleId);

        return ApiResponse.success(
                response,
                null,
                request.getRequestURI()
        );
    }
}
