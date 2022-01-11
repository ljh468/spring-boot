package com.mibank.mibank_ex00.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model){
        return "index2";
    }

    @GetMapping("/redirect")
    public String index(Model model){
        return "redirect:/home"; // 정적페이지로 보내거나 GetMapping 주소로 redirect 보냄
    }
}
