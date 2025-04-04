package com.andy.master.model.dto.response;

import com.andy.master.model.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private RoleType role;
}
