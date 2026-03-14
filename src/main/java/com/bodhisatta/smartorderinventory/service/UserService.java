package com.bodhisatta.smartorderinventory.service;

import com.bodhisatta.smartorderinventory.dto.request.UserRequest;
import com.bodhisatta.smartorderinventory.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

}
