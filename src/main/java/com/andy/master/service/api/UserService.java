package com.andy.master.service.api;

import com.andy.master.model.dto.request.UserRequest;
import com.andy.master.model.dto.response.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
}
