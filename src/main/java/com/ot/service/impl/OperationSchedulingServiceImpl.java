package com.ot.service.impl;

import org.springframework.stereotype.Service;

import com.ot.dto.scheduleOperationRequest.ScheduleOperationRequest;
import com.ot.entity.OTRoom;
import com.ot.entity.OTSchedule;
import com.ot.entity.ScheduledOperation;
import com.ot.enums.OperationStatus;
import com.ot.enums.ScheduleType;
import com.ot.repository.OTRoomRepository;
import com.ot.repository.ScheduledOperationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OperationSchedulingServiceImpl {

    private final ScheduledOperationRepository operationRepository;
    private final OTRoomRepository roomRepository;
    private final OTScheduleRepository scheduleRepository;

    @Transactional
    public void schedule(Long operationId, ScheduleOperationRequest request) {

        ScheduledOperation operation =
                operationRepository.findById(operationId)
                .orElseThrow(() -> new RuntimeException("Operation not found"));

        OTRoom room =
                roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // update operation
        operation.setRoom(room);
        operation.setPrimarySurgeonId(request.getSurgeonId());
        operation.setPrimarySurgeonName(request.getSurgeonName());
        operation.setAnesthesiologistId(request.getAnesthesiologistId());
        operation.setAnesthesiologistName(request.getAnesthesiologistName());
        operation.setScheduledStartTime(request.getStartTime());
        operation.setScheduledEndTime(request.getEndTime());
        operation.setStatus(OperationStatus.SCHEDULED);

        operationRepository.save(operation);

        // block OT slot
        OTSchedule schedule = OTSchedule.builder()
                .room(room)
                .scheduleDate(request.getStartTime())
                .timeSlot(request.getStartTime() + " - " + request.getEndTime())
                .type(ScheduleType.SURGERY)
                .blockedBy("ADMIN")
                .build();

        scheduleRepository.save(schedule);
    }
}
