package com.ot.dto.scheduleOperationRequest;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ScheduleOperationRequest {

    private Long roomId;

    private String surgeonId;
    private String surgeonName;

    private String anesthesiologistId;
    private String anesthesiologistName;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

}