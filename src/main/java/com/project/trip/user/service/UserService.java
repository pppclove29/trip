package com.project.trip.user.service;

import com.project.trip.user.entity.User;
import com.project.trip.user.model.request.UserSaveRequestDto;

public interface UserService {

    void save(UserSaveRequestDto userSaveRequestDto);
    User getUserByEmail(String email);
    boolean checkMemberByEmail(String email);

}
