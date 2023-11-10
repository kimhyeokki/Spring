package com.khk11.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public String login(){
        return null;
    }

    @GetMapping("/signup")
    public String signup(){

        return null;
    }
}
