package com.co.ufps.previo.service;

import com.co.ufps.previo.model.request.LoginRequest;
import com.co.ufps.previo.model.request.RegisterRequest;
import com.co.ufps.previo.model.response.AuthResponse;

public interface iAuthService {

    public AuthResponse login(LoginRequest request);

    public AuthResponse registerUser(RegisterRequest request);

}
