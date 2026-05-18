package io.kidan.guardian.repository;

import io.kidan.guardian.entity.ContractDetailView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ContractDetailViewRepository extends JpaRepository<ContractDetailView, String> {
    @Override @NonNull
    List<ContractDetailView> findAll();

}
