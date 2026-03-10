package com.ot.mapper;

import com.ot.dto.response.AdminResponse;
import com.ot.dto.response.HospitalSummaryResponse;
import com.ot.entity.User;

public class AdminMapper {
	
	private AdminMapper() {}
	
	public static AdminResponse toResponse(User user) {
		
		AdminResponse response = new AdminResponse();
		
        response.setId(user.getId());
        response.setName(user.getUserName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        response.setActive(user.getIsActive());
        
        if(user.getHospital()!=null) {
        	
        	HospitalSummaryResponse hospitalDto = new HospitalSummaryResponse();
        	hospitalDto.setId(user.getHospital().getId());
            hospitalDto.setName(user.getHospital().getHospitalName());

            response.setHospital(hospitalDto);
        }

        return response;
		
	}

}
