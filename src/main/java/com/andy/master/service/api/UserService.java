package com.andy.master.service.api;

import com.andy.master.model.dto.request.UserRequest;
import com.andy.master.model.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, UserResponse userDto);

    void deleteUser(Long id);
}
