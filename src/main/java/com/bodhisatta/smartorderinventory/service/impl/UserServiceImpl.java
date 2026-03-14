package com.bodhisatta.smartorderinventory.service.impl;

import com.bodhisatta.smartorderinventory.dto.request.UserRequest;
import com.bodhisatta.smartorderinventory.dto.response.UserResponse;
import com.bodhisatta.smartorderinventory.entity.Role;
import com.bodhisatta.smartorderinventory.entity.User;
import com.bodhisatta.smartorderinventory.exception.ResourceNotFoundException;
import com.bodhisatta.smartorderinventory.repository.UserRepository;
import com.bodhisatta.smartorderinventory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest request) {

        User user=User.builder().name(request.getName()).email(request.getEmail()).password(request.getPassword())
                .role(Role.USER).createdAt(LocalDateTime.now()).build();

        User savedUser=userRepository.save(user);

        return UserResponse.builder().id(savedUser.getId()).name(savedUser.getName()).email(savedUser.getEmail()).build();
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll().stream().map(user -> UserResponse.builder().id(user.getId())
                .name(user.getName()).email(user.getEmail()).build()).collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id: " + id));

        return UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).build();
    }
}
