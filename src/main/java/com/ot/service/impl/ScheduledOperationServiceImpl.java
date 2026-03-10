package com.ot.service.impl;

import java.util.UUID;
import org.springframework.stereotype.Service;
import com.ot.dto.ipdRequest.IpdOtRequest;
import com.ot.entity.Hospital;
import com.ot.entity.ScheduledOperation;
import com.ot.enums.OperationStatus;
import com.ot.exception.BadRequestException;
import com.ot.repository.HospitalRepository;
import com.ot.repository.ScheduledOperationRepository;
import com.ot.service.ScheduledOperationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduledOperationServiceImpl implements ScheduledOperationService{

    private final ScheduledOperationRepository operationRepository;

    private final HospitalRepository hospitalRepository;

    @Override
    public void createOperationFromIpd(IpdOtRequest request) {

    	//Check kro Hospital exist krta hai ya nhi!
    	
    	if(request.getIpdHospitalId()==null) {
    		throw new BadRequestException("Hospital Id Must not be Null");
    	}
        Hospital hospital = hospitalRepository.findById(request.getIpdHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        ScheduledOperation operation = ScheduledOperation.builder()
                .hospital(hospital)
                .operationReference(UUID.randomUUID().toString())
                .patientId(request.getPatientId())
                .patientName(request.getPatientName())
                .patientMrn(request.getPatientMrn())
                .ipdAdmissionId(request.getIpdAdmissionId().toString())
                .procedureName(request.getProcedureName())
                .procedureCode(request.getProcedureCode())
                .status(OperationStatus.REQUESTED)
                .createdBy("IPD_SERVICE")
                .build();

        operationRepository.save(operation);
    }
}