package com.ot.mapper;

import com.ot.dto.iVFluid.IVFluidResponse;
import com.ot.entity.IVFluidRecord;

public class IVFluidMapper {

    public static IVFluidResponse toResponse(IVFluidRecord ivFluid) {
        return IVFluidResponse.builder()
                .id(ivFluid.getId())
                .intraOpId(ivFluid.getIntraOpRecord().getId())
                .fluidType(ivFluid.getFluidType())
                .volume(ivFluid.getVolume())
                .unit(ivFluid.getUnit())
                .startTime(ivFluid.getStartTime())
                .endTime(ivFluid.getEndTime())
                .administeredBy(ivFluid.getAdministeredBy())
                .createdAt(ivFluid.getCreatedAt())
                .build();
    }
}