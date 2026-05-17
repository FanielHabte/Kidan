package io.kidan.guardian.repository;

import io.kidan.guardian.entity.CsvContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CsvContractRepository extends JpaRepository<CsvContract, String> {

    @Override @NonNull
    <S extends CsvContract> List<S> saveAll(@NonNull Iterable<S> entities);

    @Override @NonNull
    <S extends CsvContract> S save(@NonNull S entity);

}
