package com.ot.dto.iVFluid;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IVFluidSummaryResponse {

    private Long operationId;

    // Per fluid details
    private List<IVFluidResponse> fluids;

    // Totals
    private Integer totalVolumeML;          // sab fluids ka sum ML mein
    private Map<String, Integer> byFluidType; // "Normal Saline" -> 1500ml

    // Ongoing
    private Integer ongoingFluidsCount;     // endTime null wale
    private Integer completedFluidsCount;   // endTime set wale
}