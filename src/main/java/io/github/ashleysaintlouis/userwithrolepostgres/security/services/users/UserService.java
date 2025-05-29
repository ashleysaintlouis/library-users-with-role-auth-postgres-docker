package io.github.ashleysaintlouis.userwithrolepostgres.security.services.users;

import io.github.ashleysaintlouis.userwithrolepostgres.models.users.ERole;
import io.github.ashleysaintlouis.userwithrolepostgres.models.users.Role;
import io.github.ashleysaintlouis.userwithrolepostgres.models.users.User;
import io.github.ashleysaintlouis.userwithrolepostgres.payloads.request.users.LoginRequest;
import io.github.ashleysaintlouis.userwithrolepostgres.payloads.request.users.SignupRequest;
import io.github.ashleysaintlouis.userwithrolepostgres.payloads.response.JwtResponse;
import io.github.ashleysaintlouis.userwithrolepostgres.repositories.usersRepositories.RoleRepository;
import io.github.ashleysaintlouis.userwithrolepostgres.repositories.usersRepositories.UserRepository;
import io.github.ashleysaintlouis.userwithrolepostgres.security.jwt.JwtUtils;
import io.github.ashleysaintlouis.userwithrolepostgres.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserValidator validator;


    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtils jwtUtils,
                       RoleRepository roleRepository,
                       UserValidator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.roleRepository = roleRepository;
        this.validator = validator;
    }

    public User salvarUser(SignupRequest signupRequest) {
        validator.validarUser(signupRequest);
        String erroRole = "Role não encontrado!";

        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(erroRole));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(erroRole));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException(erroRole));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(erroRole));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public ResponseEntity<?> userLogin (LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles
                )
        );

    }

}
