package com.ot.service;

import java.util.List;

import com.ot.dto.request.CreateStaffRequest;
import com.ot.dto.request.CreateStaffScheduleRequest;
import com.ot.dto.request.UpdateStaffRequest;
import com.ot.dto.request.UpdateStaffScheduleRequest;
import com.ot.dto.response.StaffResponse;
import com.ot.dto.response.StaffScheduleResponse;
import com.ot.entity.StaffSchedule;

public interface StaffService {

	StaffResponse createStaff(CreateStaffRequest request);

	StaffResponse updateStaff(Long staffId, UpdateStaffRequest request);

	StaffResponse getStaff(Long staffId);

	List<StaffResponse> getAllStaff();

	String deactivateStaff(Long staffId);

	String activateStaff(Long staffId);

	String createStaffSchedule(CreateStaffScheduleRequest request);

	String updateStaffSchedule(Long scheduleId, UpdateStaffScheduleRequest request);

	List<StaffScheduleResponse> getStaffSchedule(Long staffId);

	String deleteStaffSchedule(Long scheduleId);

}
