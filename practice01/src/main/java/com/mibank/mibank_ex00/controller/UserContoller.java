package com.mibank.mibank_ex00.controller;

import com.mibank.mibank_ex00.domain.User;
import com.mibank.mibank_ex00.dto.DeptRequestDto;
import com.mibank.mibank_ex00.dto.UserRequestDto;
import com.mibank.mibank_ex00.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserContoller {

    private final UserService userService;

    @Autowired

    public UserContoller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login")
    public String login(){
        return "login";
    }

    @GetMapping("/user/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/registerUser")
    @ResponseBody
    public User registerUser(Model model){

        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setAdmin(false);
        requestDto.setUser_name("이재훈");
        requestDto.setEmail("ljh468@naver.com");
        requestDto.setUser_pwd("1234");

        DeptRequestDto dpDto = new DeptRequestDto();

        User user = userService.registerUser(requestDto);
        model.addAttribute("requestDto", requestDto);
        return user;
    }
}
