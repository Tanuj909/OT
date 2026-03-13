package com.ot.service;

import com.ot.dto.intraOp.AnesthesiaTimeRequest;
import com.ot.dto.intraOp.IntraOpRequest;
import com.ot.dto.intraOp.IntraOpResponse;
import com.ot.dto.intraOp.IntraOpStatusRequest;
import com.ot.dto.intraOp.IntraOpSummaryResponse;

public interface IntraOpService {

	IntraOpResponse createIntraOpRecord(Long operationId, IntraOpRequest request);

	IntraOpResponse getIntraOpRecord(Long operationId);

	IntraOpResponse updateIntraOpRecord(Long operationId, IntraOpRequest request);

	IntraOpResponse updateIntraOpStatus(Long operationId, IntraOpStatusRequest request);

	IntraOpResponse updateAnesthesiaTime(Long operationId, AnesthesiaTimeRequest request);

	IntraOpSummaryResponse getIntraOpSummary(Long operationId);

}
