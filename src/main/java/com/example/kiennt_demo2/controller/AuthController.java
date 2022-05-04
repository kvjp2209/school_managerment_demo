package com.example.kiennt_demo2.controller;

import com.example.kiennt_demo2.common.ApiResponse;
import com.example.kiennt_demo2.dto.request.LoginRequestDto;
import com.example.kiennt_demo2.dto.response.LoginResponseDto;
import com.example.kiennt_demo2.entity.RandomStuff;
import com.example.kiennt_demo2.entity.Role;
import com.example.kiennt_demo2.security.impl.UserDetailsImpl;
import com.example.kiennt_demo2.security.impl.UserDetailsServiceImpl;
import com.example.kiennt_demo2.security.jwt.JwtTokenProvider;
import com.example.kiennt_demo2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(new LoginResponseDto(
                jwt, userDetails.getUsername(), roles
        ));

        return ResponseEntity.status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @GetMapping("/employee")
    public ApiResponse getAllEmployees() {
        ApiResponse result = employeeService.getAllEmployees();
        return result;
    }

    @GetMapping("/random")
    public RandomStuff randomStuff() {
        RandomStuff randomStuff = new RandomStuff("Kien");
        return randomStuff;
    }
}
