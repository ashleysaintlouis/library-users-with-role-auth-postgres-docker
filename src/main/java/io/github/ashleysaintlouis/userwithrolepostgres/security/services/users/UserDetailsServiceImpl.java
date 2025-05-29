package io.github.ashleysaintlouis.userwithrolepostgres.security.services.users;

import io.github.ashleysaintlouis.userwithrolepostgres.models.users.User;
import io.github.ashleysaintlouis.userwithrolepostgres.repositories.usersRepositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado com o " + username));

        return UserDetailsImpl.build(user);

    }
}
