package com.ot.dto.request;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Data
public class CreateStaffScheduleRequest {

    private Long staffId;

    private List<DayOfWeek> days;

    private LocalTime startTime;

    private LocalTime endTime;

}