package com.ot.dto.response;


import java.time.LocalDate;
import java.time.LocalTime;

import com.ot.enums.StaffAvailabilityStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaffAvailabilityResponse {

    private Long id;

    private Long staffId;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private StaffAvailabilityStatus status;
}