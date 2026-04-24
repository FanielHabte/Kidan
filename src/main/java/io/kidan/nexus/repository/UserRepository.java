package io.kidan.nexus.repository;

import io.kidan.nexus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Override
    Optional<User> findById(String s);

    Optional<User> findByEmail (String s);

    @Override
    <S extends User> S save(S entity);
}
