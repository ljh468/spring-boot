package com.mibank.mibank_ex00.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Dept {

    @Id @GeneratedValue
    private Long dept_seq;

    private String dept_name;

    @OneToMany(mappedBy = "dept")
    private List<User> userList = new ArrayList<>();

}
