package com.project.trip.user.service;

import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.user.entity.User;
import com.project.trip.user.model.request.AdditionInfoUserSaveRequestDto;

public interface UserService {

    void save(AdditionInfoUserSaveRequestDto additionInfoUserSaveRequestDto, CustomOauthUser oauthUser);

    User getUserByEmail(String email);

    boolean checkMemberByEmail(String email);
}
