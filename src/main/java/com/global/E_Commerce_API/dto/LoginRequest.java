package com.global.E_Commerce_API.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }


    public String getEmail() {
        return email;
    }
}
