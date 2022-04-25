package com.example.kiennt_demo2.controller;

import com.example.kiennt_demo2.common.ApiResponse;
import com.example.kiennt_demo2.dto.LoginRequestDto;
import com.example.kiennt_demo2.dto.SignUpRequestDto;
import com.example.kiennt_demo2.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("k1/")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        ApiResponse apiResponse = loginService.signUp(signUpRequestDto);

        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequestDto loginRequestDto) {

        ApiResponse apiResponse = loginService.login(loginRequestDto);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
