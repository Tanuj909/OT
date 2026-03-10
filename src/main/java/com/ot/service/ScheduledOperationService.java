package com.ot.service;

import com.ot.dto.ipdRequest.IpdOtRequest;

public interface ScheduledOperationService {

	void createOperationFromIpd(IpdOtRequest request);

}
