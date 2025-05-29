package io.github.ashleysaintlouis.userwithrolepostgres.controllers.auth;


import io.github.ashleysaintlouis.userwithrolepostgres.payloads.request.users.LoginRequest;
import io.github.ashleysaintlouis.userwithrolepostgres.payloads.request.users.SignupRequest;
import io.github.ashleysaintlouis.userwithrolepostgres.security.services.users.UserService;
import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("usuario")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        userService.userLogin(loginRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        userService.salvarUser(signupRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(signupRequest.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }
}
