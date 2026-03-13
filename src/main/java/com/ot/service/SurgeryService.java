package com.ot.service;

import com.ot.dto.surgeryResponse.SurgeryStartResponse;

public interface SurgeryService {

	SurgeryStartResponse startSurgery(Long operationId);

}
