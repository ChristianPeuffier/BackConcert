package fr.istic.taa.jaxrs.utils;

public class AuthResponse {
    private String token;
    private final Long id;

    public AuthResponse(final String token, final Long id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }
}
