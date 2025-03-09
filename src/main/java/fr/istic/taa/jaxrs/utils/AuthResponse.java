package fr.istic.taa.jaxrs.utils;

public class AuthResponse {
    private String token;

    public AuthResponse(final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
