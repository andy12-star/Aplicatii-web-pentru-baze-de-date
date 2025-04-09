package com.andy.master.service.impl;

import com.andy.master.model.dto.request.RegisterRequest;
import com.andy.master.model.dto.response.UserResponse;
import com.andy.master.model.entity.Profile;
import com.andy.master.model.entity.User;
import com.andy.master.model.enums.RoleType;
import com.andy.master.repository.ProfileRepository;
import com.andy.master.repository.UserRepository;
import com.andy.master.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        User savedUser = userRepository.save(user);

        Profile profile = Profile.builder()
                .fullName(savedUser.getFirstName() + " " + savedUser.getLastName())
                .user(savedUser)
                .build();

        profileRepository.save(profile);

        return mapToDto(savedUser);
    }

    @Override
    public void createAdmin() {
        if (userRepository.existsByEmail("andy@admin.com")) {
            throw new RuntimeException("Admin already exists");
        }

        User admin = User.builder()
                .firstName("Admin")
                .lastName("Master")
                .email("andy@admin.com")
                .phoneNumber("0712345678")
                .password(passwordEncoder.encode("admin123"))
                .role(RoleType.ADMIN)
                .build();
        User savedUser = userRepository.save(admin);
        Profile profile = Profile.builder()
                .fullName("Admin Master")
                .user(savedUser)
                .build();
        profileRepository.save(profile);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDto(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();

    }

    @Override
    public UserResponse updateUser(Long id, UserResponse userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        User updated = userRepository.save(user);
        return mapToDto(updated);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponse mapToDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }

}
