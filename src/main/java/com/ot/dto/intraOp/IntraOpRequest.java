package com.ot.dto.intraOp;

import com.ot.enums.VolumeUnit;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class IntraOpRequest {

    private String procedurePerformed;
    private String incisionType;
    private String woundClosure;

    private Integer bloodLoss;

    @Enumerated(EnumType.STRING)
    private VolumeUnit bloodLossUnit;

    private String urineOutput;
    private String drainOutput;

    private String intraOpFindings;
    private String specimensCollected;
    private String implantsUsed;

    private String complications;
    private String interventions;
}
