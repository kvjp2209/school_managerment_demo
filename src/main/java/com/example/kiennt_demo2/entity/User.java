package com.example.kiennt_demo2.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.engine.internal.Cascade;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "address")
    private String address;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(Long id, String username, String password, String name, int age, String address, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.address = address;
        this.roles = roles;
    }

    public User(String username, String password, String name, int age, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public User(String username, String password, String name, int age, String address, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.address = address;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", roles=" + roles +
                '}';
    }
}
