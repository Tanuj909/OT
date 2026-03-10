package com.ot.service;

import java.util.List;

import com.ot.dto.operationtheaterRequest.OperationTheaterCreateRequest;
import com.ot.dto.operationtheaterResponse.OperationTheaterResponse;


public interface OperationTheaterService {

    OperationTheaterResponse create(OperationTheaterCreateRequest request);

    List<OperationTheaterResponse> getAll();

    OperationTheaterResponse getById(Long id);

    OperationTheaterResponse update(Long id, OperationTheaterCreateRequest request);

    void delete(Long id);
}
