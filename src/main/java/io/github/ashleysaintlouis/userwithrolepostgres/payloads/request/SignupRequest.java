package io.github.ashleysaintlouis.userwithrolepostgres.payloads.request;

import io.github.ashleysaintlouis.userwithrolepostgres.models.users.ERole;
import io.github.ashleysaintlouis.userwithrolepostgres.models.users.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 6, max = 20)
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 250)
    private String password;

    private Set<Role> roles;

    public Set<ERole> getRole() {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }
}
