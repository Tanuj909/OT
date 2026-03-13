package com.ot.dto.iVFluid;

import java.time.LocalDateTime;

import com.ot.enums.VolumeUnit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IVFluidResponse {
    private Long id;
    private Long intraOpId;
    private String fluidType;
    private Integer volume;
    private VolumeUnit unit;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String administeredBy;
    private LocalDateTime createdAt;
}
