package com.project.trip.user.controller;

import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.user.entity.Role;
import com.project.trip.user.entity.User;
import com.project.trip.user.model.request.AdditionInfoUserSaveRequestDto;
import com.project.trip.user.service.UserServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/users")
    public ResponseEntity<?> save(@RequestBody AdditionInfoUserSaveRequestDto additionInfoUserSaveRequestDto,
                                  @AuthenticationPrincipal CustomOauthUser user) {
        userService.save(additionInfoUserSaveRequestDto, user);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    //TODO 테스트 끝나면 지울 것
    @GetMapping("/user/save/temp")
    public void temp(@AuthenticationPrincipal CustomOauthUser user){
        System.out.println("temp 실행");
        AdditionInfoUserSaveRequestDto additionInfoUserSaveRequestDto = new AdditionInfoUserSaveRequestDto();
        additionInfoUserSaveRequestDto.setPhoneNumber("!!!!!!!!");
        userService.save(additionInfoUserSaveRequestDto, user);
    }
}
