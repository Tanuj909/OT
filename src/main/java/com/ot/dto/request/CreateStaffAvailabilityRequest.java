package com.ot.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ot.enums.StaffAvailabilityStatus;

@Data
public class CreateStaffAvailabilityRequest {

    private Long staffId;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;
    
    private StaffAvailabilityStatus status;
}