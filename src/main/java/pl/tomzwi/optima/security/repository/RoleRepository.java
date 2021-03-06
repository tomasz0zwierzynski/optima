package pl.tomzwi.optima.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tomzwi.optima.security.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
