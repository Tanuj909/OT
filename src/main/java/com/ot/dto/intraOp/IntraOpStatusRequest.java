package com.ot.dto.intraOp;

import com.ot.enums.SurgeryStatus;

import lombok.Data;

@Data
public class IntraOpStatusRequest {
    private SurgeryStatus status;
    private String reason; // ABORTED ya COMPLICATED ke liye mandatory
}