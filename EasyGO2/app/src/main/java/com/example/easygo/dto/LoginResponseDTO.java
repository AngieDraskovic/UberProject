package com.example.easygo.dto;

public class LoginResponseDTO {

    private String accessToken;
    private String refreshToken;

    public LoginResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return this.accessToken;

    }

    public void setAccessToken(String token){
        this.accessToken = token;

    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
