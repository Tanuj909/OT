package com.ot.mapper;

import com.ot.dto.response.StaffScheduleResponse;
import com.ot.entity.StaffSchedule;

public class StaffScheduleMapper {
	
    public static StaffScheduleResponse toResponse(StaffSchedule schedule) {

        StaffScheduleResponse res = new StaffScheduleResponse();
        res.setId(schedule.getId());
        res.setDayOfWeek(schedule.getDayOfWeek());
        res.setStartTime(schedule.getStartTime());
        res.setEndTime(schedule.getEndTime());

        return res;
    }
	
}
