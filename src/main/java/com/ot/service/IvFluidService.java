package com.ot.service;

import java.util.List;

import com.ot.dto.iVFluid.IVFluidRequest;
import com.ot.dto.iVFluid.IVFluidResponse;
import com.ot.dto.iVFluid.IVFluidSummaryResponse;
import com.ot.dto.iVFluid.IVFluidUpdateRequest;

public interface IvFluidService {

	IVFluidResponse addIVFluid(Long operationId, IVFluidRequest request);

	List<IVFluidResponse> getIVFluids(Long operationId);

	void removeIVFluid(Long operationId, Long fluidId);

	IVFluidResponse updateIVFluid(Long operationId, Long fluidId, IVFluidUpdateRequest request);

	IVFluidResponse completeIVFluid(Long operationId, Long fluidId);

	IVFluidSummaryResponse getIVFluidSummary(Long operationId);

}
