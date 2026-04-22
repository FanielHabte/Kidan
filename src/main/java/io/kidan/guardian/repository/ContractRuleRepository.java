package io.kidan.guardian.repository;

import io.kidan.guardian.entity.ContractRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface ContractRuleRepository extends JpaRepository <ContractRule, String> {
    @Override @NonNull
    Optional<ContractRule> findById(@NonNull String id);

}
