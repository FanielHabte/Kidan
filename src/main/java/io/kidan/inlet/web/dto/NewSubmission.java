package io.kidan.inlet.web.dto;

import io.kidan.guardian.entity.contract.Contract;
import io.kidan.guardian.entity.dataset.Dataset;

public record NewSubmission(Contract contract, Dataset dataset) {
}
