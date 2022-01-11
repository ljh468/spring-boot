package com.mibank.mibank_ex00.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String user_name;
    private String user_pwd;
    private String email;
    private boolean admin = false;
    private String adminToken = "";

}
