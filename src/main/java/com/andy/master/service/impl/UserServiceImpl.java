package com.andy.master.service.impl;

import com.andy.master.model.dto.request.UserRequest;
import com.andy.master.model.dto.response.UserResponse;
import com.andy.master.model.entity.Profile;
import com.andy.master.model.entity.User;
import com.andy.master.model.enums.RoleType;
import com.andy.master.repository.ProfileRepository;
import com.andy.master.repository.UserRepository;
import com.andy.master.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(bCryptPasswordEncoder.encode(userRequest.getPassword()))
                .role(RoleType.CLIENT)
                .build();

        User savedUser = userRepository.save(user);

        Profile profile = Profile.builder()
                .fullName(user.getFirstName() + " " + user.getLastName())
                .user(savedUser)
                .build();

        profileRepository.save(profile);

        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .phoneNumber(savedUser.getPhoneNumber())
                .role(savedUser.getRole())
                .build();
    }
}
