package com.ot.dto.iVFluid;

import java.time.LocalDateTime;

import com.ot.enums.VolumeUnit;

import lombok.Data;

@Data
public class IVFluidUpdateRequest {
    private Integer volume;
    private VolumeUnit unit;
    private LocalDateTime endTime;
}