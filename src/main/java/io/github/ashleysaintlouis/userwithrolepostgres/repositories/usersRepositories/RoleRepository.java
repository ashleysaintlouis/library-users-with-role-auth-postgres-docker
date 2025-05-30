package io.github.ashleysaintlouis.userwithrolepostgres.repositories.usersRepositories;

import io.github.ashleysaintlouis.userwithrolepostgres.models.users.ERole;
import io.github.ashleysaintlouis.userwithrolepostgres.models.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
