package com.ot.dto.intraOp;

import java.time.LocalDateTime;

import com.ot.enums.SurgeryStatus;
import com.ot.enums.VolumeUnit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntraOpResponse {

    private Long id;
    private Long operationId;

    private LocalDateTime surgeryStartTime;  // ScheduledOperation.actualStartTime se
    private LocalDateTime anesthesiaStartTime;
    private LocalDateTime anesthesiaEndTime;

    private String procedurePerformed;
    private String incisionType;
    private String woundClosure;

    private Integer bloodLoss;
    private VolumeUnit bloodLossUnit;

    private String urineOutput;
    private String drainOutput;

    private String intraOpFindings;
    private String specimensCollected;
    private String implantsUsed;

    private String complications;
    private String interventions;

    private SurgeryStatus status;
    private LocalDateTime createdAt;
    private String createdBy;
}
