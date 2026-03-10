package com.ot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ot.dto.ipdRequest.IpdOtRequest;
import com.ot.service.ScheduledOperationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ipd")
@RequiredArgsConstructor
public class IpdMimicController {

    private final ScheduledOperationService scheduledOperationService;

    @PostMapping("/ot-request")
    public ResponseEntity<String> createOtRequest(@RequestBody IpdOtRequest request) {

        scheduledOperationService.createOperationFromIpd(request);

        return ResponseEntity.ok("OT request received successfully");

    }
}