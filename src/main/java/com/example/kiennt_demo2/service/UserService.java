package com.example.kiennt_demo2.service;

import com.example.kiennt_demo2.common.ApiResponse;
import com.example.kiennt_demo2.dto.UserRequestDto;
import com.example.kiennt_demo2.entity.Role;
import com.example.kiennt_demo2.entity.User;
import com.example.kiennt_demo2.repository.RoleRepository;
import com.example.kiennt_demo2.repository.UserRepository;
import com.example.kiennt_demo2.utils.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @Transactional
    public ApiResponse createUser(UserRequestDto userRequestDto) {
        ApiResponse apiResponse = new ApiResponse();

        if (userRepository.findByUsername(userRequestDto.getUsername()) != null) {
            apiResponse.setError("Username existed");
            return apiResponse;
        }

        User user = new User(
                userRequestDto.getUsername()
                , passwordEncoder.encode(userRequestDto.getPassword())
                , userRequestDto.getName()
                , userRequestDto.getAge()
                , userRequestDto.getAddress());

        Set<String> strRoles = userRequestDto.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role role = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(role);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("ERROR: Role is not found!"));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("ERROR: Role is not found!"));
                        roles.add(userRole);

                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        apiResponse.setData("Success!!!");

        return apiResponse;
    }


    public ApiResponse getAllUser() {
        ApiResponse apiResponse = new ApiResponse();
        List<User> users = userRepository.findAll();

        apiResponse.setData(users);
        return apiResponse;
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public ApiResponse getUserById(long id) {
        ApiResponse apiResponse = new ApiResponse();
        User user = userRepository.findById(id);
        if (user == null) {
            apiResponse.setError("Not Found!!!");
            return apiResponse;
        }
        apiResponse.setData(user);

        return apiResponse;
    }

    public ApiResponse deleteUser(long id) {
        ApiResponse apiResponse = new ApiResponse();
        User user = userRepository.findById(id);
        if (user == null) {
            apiResponse.setError("Not Found!!!");
            return apiResponse;
        }
        userRepository.delete(user);
        apiResponse.setData("Delete success!!!");

        return apiResponse;
    }

    @Transactional
    public ApiResponse updateUser(UserRequestDto userRequestDto, long id) {
        ApiResponse apiResponse = new ApiResponse();
        User user = userRepository.findByUsername(userRequestDto.getUsername());
        if (user == null) {
            apiResponse.setError("Not Found!!!");
            return apiResponse;
        }
        if (userRequestDto.getUsername() == user.getUsername() || user.getId() != id) {
            apiResponse.setError("Can't change username or ID input not match with username!!!");
            return apiResponse;
        }
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setName(userRequestDto.getName());
        user.setAge((userRequestDto.getAge()));
        user.setAddress(userRequestDto.getAddress());

        userRepository.save(user);

        apiResponse.setData(user);

        return apiResponse;
    }
}
