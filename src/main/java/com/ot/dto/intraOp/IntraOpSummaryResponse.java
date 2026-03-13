package com.ot.dto.intraOp;

import java.time.LocalDateTime;
import java.util.List;

import com.ot.dto.iVFluid.IVFluidResponse;
import com.ot.enums.SurgeryStatus;
import com.ot.enums.VolumeUnit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntraOpSummaryResponse {

    // Operation info
    private Long operationId;
    private String operationReference;
    private String patientName;
    private String procedureName;

    // Timing
    private LocalDateTime surgeryStartTime;
    private LocalDateTime surgeryEndTime;
    private Long surgeryDurationMinutes;    // calculated
    private LocalDateTime anesthesiaStartTime;
    private LocalDateTime anesthesiaEndTime;
    private Long anesthesiaDurationMinutes; // calculated

    // Surgery details
    private String procedurePerformed;
    private String incisionType;
    private String woundClosure;
    private String intraOpFindings;
    private String complications;
    private String interventions;
    private String specimensCollected;
    private String implantsUsed;

    // Fluid summary
    private Integer totalBloodLoss;
    private VolumeUnit bloodLossUnit;
    private String urineOutput;
    private String drainOutput;
    private Integer totalIVFluidsML;        // all IV fluids sum in ML
    private List<IVFluidResponse> ivFluids;

    // Team
    private String primarySurgeon;
    private String anesthesiologist;

    // Status
    private SurgeryStatus status;
}