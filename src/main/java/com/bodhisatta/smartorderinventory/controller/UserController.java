package com.bodhisatta.smartorderinventory.controller;

import com.bodhisatta.smartorderinventory.dto.request.UserRequest;
import com.bodhisatta.smartorderinventory.dto.response.UserResponse;
import com.bodhisatta.smartorderinventory.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest request)
    {
        return userService.createUser(request);
    }

    @GetMapping
    public List<UserResponse> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id)
    {
        return userService.getUserById(id);
    }
}
