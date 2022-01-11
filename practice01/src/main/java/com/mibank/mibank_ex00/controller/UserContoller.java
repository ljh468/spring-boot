package com.mibank.mibank_ex00.controller;

import com.mibank.mibank_ex00.domain.User;
import com.mibank.mibank_ex00.dto.UserRequestDto;
import com.mibank.mibank_ex00.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

//    @GetMapping("/user/signup")
//    public String signup(){
//        return "signup";
//    }

    @RequestMapping("/registerUser")
    public User registerUser(Model model){

        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setAdmin(false);
        requestDto.setUser_name("이재훈");
        requestDto.setEmail("ljh468@naver.com");
        requestDto.setDept_name("데이터분석과");
        requestDto.setUser_pwd("1234");

        User user = userService.registerUser(requestDto);
        model.addAttribute("requestDto", requestDto);
        return user;
    }
}
