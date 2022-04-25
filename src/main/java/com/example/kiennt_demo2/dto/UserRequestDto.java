package com.example.kiennt_demo2.dto;

import com.example.kiennt_demo2.entity.Role;
import com.sun.istack.NotNull;

import java.util.Set;

public class UserRequestDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String name;
    private int age;
    private String address;
    private Set<String> role;

    public UserRequestDto() {
    }

    public UserRequestDto(String username, String password, String name, int age, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public UserRequestDto(String username, String password, String name, int age, String address, Set<String> role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.address = address;
        this.role = role;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
