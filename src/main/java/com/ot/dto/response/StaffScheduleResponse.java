package com.ot.dto.response;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.Data;

@Data
public class StaffScheduleResponse {

    private Long id;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

}