package io.kidan.inlet.repository;

import io.kidan.inlet.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InletRepository extends JpaRepository<Submission, String> {
    @Override @NonNull
    List<Submission> findAll();

    @Override @NonNull
    Optional<Submission> findById(@NonNull String s);

    @Override @NonNull
    <S extends Submission> S save(@NonNull S entity);

    @Override @NonNull
    <S extends Submission> List<S> saveAll(@NonNull Iterable<S> entities);

    List<Submission> findAllByDatasetId(String datasetId);
}
