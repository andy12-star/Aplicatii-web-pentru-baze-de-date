package com.andy.master.model.dto.request;

import com.andy.master.model.enums.RoleType;
import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private RoleType role;
}
