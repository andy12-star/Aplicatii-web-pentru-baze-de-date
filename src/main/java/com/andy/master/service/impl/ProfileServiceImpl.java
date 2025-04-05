package com.andy.master.service.impl;

import com.andy.master.model.entity.Profile;
import com.andy.master.repository.ProfileRepository;
import com.andy.master.service.api.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService
{
    private final ProfileRepository profileRepository;

    @Override
    public Profile getProfileById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    @Override
    public Profile updateProfile(Long id, Profile updatedProfile) {
        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        existingProfile.setFullName(updatedProfile.getFullName());
        existingProfile.setBio(updatedProfile.getBio());
        existingProfile.setProfilePictureUrl(updatedProfile.getProfilePictureUrl());
        existingProfile.setAddress(updatedProfile.getAddress());

        return profileRepository.save(existingProfile);
    }
}
