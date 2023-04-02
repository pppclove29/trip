package com.project.trip.user.service;

import com.project.trip.user.entity.User;
import com.project.trip.user.model.request.UserSaveRequestDto;
import com.project.trip.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void save(UserSaveRequestDto userSaveRequestDto) {
        User user = User.fromDto(userSaveRequestDto);

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
