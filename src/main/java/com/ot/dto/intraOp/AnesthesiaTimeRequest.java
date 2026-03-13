package com.ot.dto.intraOp;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AnesthesiaTimeRequest {
    private LocalDateTime anesthesiaStartTime;
    private LocalDateTime anesthesiaEndTime;
}