package com.ot.dto.surgeryResponse;

import java.time.LocalDateTime;
import java.util.List;

import com.ot.enums.OperationStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SurgeryStartResponse {
    private Long operationId;
    private OperationStatus status;
    private LocalDateTime actualStartTime;
    private String startedBy;
    private List<String> warnings; // lenient checks ke warnings
}