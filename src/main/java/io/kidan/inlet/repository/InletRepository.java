package io.kidan.inlet.repository;

import io.kidan.inlet.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InletRepository extends JpaRepository<Submission, String> {
    @Override
    List<Submission> findAll();

    @Override
    Optional<Submission> findById(String s);
}
