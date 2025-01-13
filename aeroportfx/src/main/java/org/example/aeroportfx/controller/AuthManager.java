package org.example.aeroportfx.controller;

public class AuthManager {
    private static AuthManager instance;
    private String accessToken;

    private AuthManager() {
    }

    public static synchronized AuthManager getInstance() {
        if (instance == null) {
            instance = new AuthManager();
        }
        return instance;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        System.out.println("Токен доступа установлен: " + accessToken);
    }

    public String getAccessToken() {
        System.out.println("Получен токен доступа: " + accessToken);
        return accessToken;
    }
}

