package com.ot.dto.request;

import java.time.DayOfWeek;
import java.time.LocalTime;
import lombok.Data;

@Data
public class UpdateStaffScheduleRequest {

    private DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;
}
