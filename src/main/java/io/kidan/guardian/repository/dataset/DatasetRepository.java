package io.kidan.guardian.repository.dataset;

import io.kidan.guardian.entity.dataset.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatasetRepository extends JpaRepository <Dataset, String> {
    @NonNull
    Optional<Dataset> findByIdAndUserId(@NonNull String id, String userId);

    @NonNull
    List<Dataset> findAllByUserId(String userId);

    @Override @NonNull
    <S extends Dataset> S save(@NonNull S dataset);

}
