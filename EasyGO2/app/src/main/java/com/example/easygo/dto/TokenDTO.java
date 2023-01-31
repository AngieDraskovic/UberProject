package com.example.easygo.dto;

public class TokenDTO {
    private static volatile TokenDTO instance;
    private String token;
    private String refreshToken;

    public TokenDTO() {

    }

    public TokenDTO(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }


    public String getToken() {
        if(this.token==null){
            return "";
        }
        return this.token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(){
        this.refreshToken = null;
    }

    public void setAccessToken(String token) {
        this.token = token;
    }

    public static TokenDTO getInstance() {
        if (instance == null) {
            synchronized (TokenDTO.class) {
                if (instance == null) {
                    instance = new TokenDTO();
                }
            }
        }
        return instance;
    }
}