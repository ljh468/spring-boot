package com.mibank.mibank_ex00.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Setter
@Getter
@Entity
public class User {

    @Id @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String email;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "dept_seq")
    private Dept dept;

    @Enumerated
    private UserRoleEnum role;

    public User() {
    }

    // 일반 사용자를 위한 생성자
    public User(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dept=" + dept +
                ", role=" + role +
                '}';
    }
}
