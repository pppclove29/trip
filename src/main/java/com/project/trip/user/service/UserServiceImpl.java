package com.project.trip.user.service;

import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.user.entity.User;
import com.project.trip.user.model.request.AdditionInfoUserSaveRequestDto;
import com.project.trip.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void save(AdditionInfoUserSaveRequestDto additionInfoUserSaveRequestDto, CustomOauthUser oauthUser) {
        User user = User.of(additionInfoUserSaveRequestDto,oauthUser);

        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean checkMemberByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
