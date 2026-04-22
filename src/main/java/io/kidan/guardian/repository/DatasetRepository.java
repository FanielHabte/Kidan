package io.kidan.guardian.repository;

import io.kidan.guardian.entity.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatasetRepository extends JpaRepository <Dataset, String> {
    @Override @NonNull
    Optional<Dataset> findById(@NonNull String id);

    @Override @NonNull
    List<Dataset> findAll();

    @Override @NonNull
    <S extends Dataset> S save(@NonNull S dataset);

}
