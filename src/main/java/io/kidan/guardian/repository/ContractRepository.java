package io.kidan.guardian.repository;

import io.kidan.guardian.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface ContractRepository extends JpaRepository <Contract, String> {
    @Override @NonNull
    Optional<Contract> findById(@NonNull String id);

}
