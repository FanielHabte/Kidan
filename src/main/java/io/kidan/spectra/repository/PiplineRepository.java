package io.kidan.spectra.repository;

import io.kidan.spectra.entity.PipelineRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface PiplineRepository extends JpaRepository<PipelineRun, String> {

    @Override @NonNull
    <S extends PipelineRun> S save(@NonNull S entity);
}
