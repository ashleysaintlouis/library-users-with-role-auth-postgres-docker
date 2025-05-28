package io.github.ashleysaintlouis.userwithrolepostgres.payloads.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


import java.util.Set;

public class SignupRequest {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

    @NotBlank
    @Size(min = 6, max = 20)
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 250)
    private String password;

    private Set<String> role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
