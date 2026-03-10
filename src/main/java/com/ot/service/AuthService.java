package com.ot.service;

import com.ot.dto.request.LoginRequest;
import com.ot.dto.response.LoginResponse;

public interface AuthService {

	LoginResponse login(LoginRequest request);

}
