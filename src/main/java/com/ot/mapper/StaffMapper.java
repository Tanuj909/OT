package com.ot.mapper;

import com.ot.dto.response.HospitalSummaryResponse;
import com.ot.dto.response.StaffResponse;
import com.ot.entity.User;

public class StaffMapper {
	
	private StaffMapper() {}
	
	public static StaffResponse toResponse(User user) {
		
		StaffResponse response = new StaffResponse();
	    response.setId(user.getId());
	    response.setName(user.getUserName());
	    response.setEmail(user.getEmail());
	    response.setRole(user.getRole());
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
