package com.ot.mapper;

import java.time.LocalDateTime;

import com.ot.dto.intraOp.IntraOpResponse;
import com.ot.entity.IntraOpRecord;

public class IntraOpMapper {

    public static IntraOpResponse toResponse(IntraOpRecord intraOp, LocalDateTime surgeryStartTime) {
        return IntraOpResponse.builder()
                .id(intraOp.getId())
                .operationId(intraOp.getScheduledOperation().getId())
                .surgeryStartTime(surgeryStartTime)         // ScheduledOperation se
                .anesthesiaStartTime(intraOp.getAnesthesiaStartTime())
                .anesthesiaEndTime(intraOp.getAnesthesiaEndTime())
                .procedurePerformed(intraOp.getProcedurePerformed())
                .incisionType(intraOp.getIncisionType())
                .woundClosure(intraOp.getWoundClosure())
                .bloodLoss(intraOp.getBloodLoss())
                .bloodLossUnit(intraOp.getBloodLossUnit())
                .urineOutput(intraOp.getUrineOutput())
                .drainOutput(intraOp.getDrainOutput())
                .intraOpFindings(intraOp.getIntraOpFindings())
                .specimensCollected(intraOp.getSpecimensCollected())
                .implantsUsed(intraOp.getImplantsUsed())
                .complications(intraOp.getComplications())
                .interventions(intraOp.getInterventions())
                .status(intraOp.getStatus())
                .createdAt(intraOp.getCreatedAt())
                .createdBy(intraOp.getCreatedBy())
                .build();
    }
}