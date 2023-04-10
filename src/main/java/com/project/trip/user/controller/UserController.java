package com.project.trip.user.controller;

import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.user.model.request.AdditionInfoUserSaveRequestDto;
import com.project.trip.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping
    public String temp(){
        return "userSave";
    }
    @PostMapping
    public String save(AdditionInfoUserSaveRequestDto additionInfoUserSaveRequestDto,
                       @AuthenticationPrincipal CustomOauthUser user){
        userService.save(additionInfoUserSaveRequestDto, user);

        return "index";
    }
}
