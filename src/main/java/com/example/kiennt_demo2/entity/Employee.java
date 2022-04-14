package com.example.kiennt_demo2.entity;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "`id`")
    private long id;

    @Column(name = "`name`", columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @Column(name = "`age`", columnDefinition = "INT(11) DEFAULT NULL", nullable = true)
    private int age;

    @Column(name = "`address`", columnDefinition = "VARCHAR(255) DEFAULT NULL", nullable = true)
    private String address;

    @Column(name = "`role_id`", columnDefinition = "VARCHAR(255)", nullable = false)
    private int roleId;


    public Employee() {
    }

    public Employee(long id, String name, int age, String address, int roleId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.roleId = roleId;
    }

    public Employee(String name, int age, String address, int roleId) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
