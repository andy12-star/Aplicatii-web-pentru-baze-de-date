package com.andy.master.model.dto.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;

    private String password;
}
