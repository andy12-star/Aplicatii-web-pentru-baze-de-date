package com.andy.master.service.api;

import com.andy.master.model.entity.Profile;

public interface ProfileService {
    Profile getProfileById(Long id);
    Profile updateProfile(Long id,Profile profile);
}
