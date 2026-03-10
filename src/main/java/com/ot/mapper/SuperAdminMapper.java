package com.ot.mapper;

import com.ot.dto.response.SuperAdminResponse;
import com.ot.entity.User;

public class SuperAdminMapper {
	
	private SuperAdminMapper() {}
	
	public static SuperAdminResponse toResponse(User user) {

        SuperAdminResponse response = new SuperAdminResponse();

        response.setId(user.getId());
        response.setName(user.getUserName());
        response.setEmail(user.getEmail());
        response.setActive(user.getIsActive());
        response.setCreatedAt(user.getCreatedAt());

        return response;
    }

}
