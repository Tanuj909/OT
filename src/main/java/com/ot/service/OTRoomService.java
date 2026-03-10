package com.ot.service;

import java.util.List;

import com.ot.dto.otRoomRequest.OTRoomCreateRequest;
import com.ot.dto.otRoomRequest.UpdateRoomStatusRequest;
import com.ot.dto.otRoomResponse.OTRoomResponse;


public interface OTRoomService {

    OTRoomResponse create(OTRoomCreateRequest request);

    List<OTRoomResponse> getAll();

    List<OTRoomResponse> getByTheater(Long theaterId);

    OTRoomResponse getById(Long id);

    OTRoomResponse update(Long id, OTRoomCreateRequest request);

    void delete(Long id);

	OTRoomResponse updateStatus(Long id, UpdateRoomStatusRequest request);

	List<OTRoomResponse> getAvailableRooms();

	void enableRoom(Long id);

	void disableRoom(Long id);
}