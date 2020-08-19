package pl.tomzwi.optima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tomzwi.optima.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

}
