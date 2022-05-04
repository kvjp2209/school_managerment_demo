package com.example.kiennt_demo2.service;

import com.example.kiennt_demo2.common.ApiResponse;
import com.example.kiennt_demo2.dto.request.LoginRequestDto;
import com.example.kiennt_demo2.dto.SignUpRequestDto;
import com.example.kiennt_demo2.entity.User;
import com.example.kiennt_demo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public ApiResponse signUp(SignUpRequestDto signUpRequestDto) {
        ApiResponse apiResponse = new ApiResponse();

        //validation

        //dto to entity
        User user = new User();
        user.setUsername(signUpRequestDto.getUsername());
        user.setPassword(signUpRequestDto.getPassword());
        user.setName(signUpRequestDto.getName());
        user.setAge(signUpRequestDto.getAge());
        user.setAddress(signUpRequestDto.getAddress());

        //store entity
        userRepository.save(user);
        //return
        apiResponse.setData("user create success!!\n" + user);

        return apiResponse;
    }

    public ApiResponse login(LoginRequestDto loginRequestDto) {
        ApiResponse apiResponse = new ApiResponse();

        //validation

        //verify user exited with given username and pass
        User user = userRepository.findOneByUsernameIgnoreCaseAndPassword(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        // response
        if (user == null) {
            apiResponse.setData("User not found");
            return apiResponse;
        }
        apiResponse.setData("User logged in: " + user);

        return apiResponse;
    }
}
