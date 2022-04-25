package com.example.kiennt_demo2.controller;

import com.example.kiennt_demo2.common.ApiResponse;
import com.example.kiennt_demo2.dto.UserRequestDto;
import com.example.kiennt_demo2.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse createUser(@RequestBody UserRequestDto userRequestDto) {
        ApiResponse apiResponse = userService.createUser(userRequestDto);
        return apiResponse;
    }

    @GetMapping(value = "/getall")
    public ApiResponse getAllUsers() {
//        SecurityContextHolder.getContext().getAuthentication();
////        SecurityContextHolderAwareRequestWrapper
//        request.isUserInRole("ADMIN");
//        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        ApiResponse apiResponse = userService.getAllUser();
        return apiResponse;
    }

    @GetMapping(value = "/{id}")
    public ApiResponse getUser(@PathVariable("id") long id) {
        ApiResponse apiResponse = userService.getUserById(id);
        return apiResponse;
    }

    @PutMapping(value = "/{id}")
    public ApiResponse updateUser(@Validated @RequestBody UserRequestDto userRequestDto, @PathVariable("id") long id) {
        ApiResponse apiResponse = userService.updateUser(userRequestDto, id);
        return apiResponse;
    }

    @DeleteMapping(value = "/{id}")
    public ApiResponse deleteUser(@PathVariable("id") long id) {
        ApiResponse apiResponse = userService.deleteUser(id);
        return apiResponse;
    }
}
