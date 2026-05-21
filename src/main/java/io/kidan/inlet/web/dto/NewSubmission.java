package io.kidan.inlet.web.dto;

import io.kidan.guardian.entity.Contract;
import io.kidan.guardian.entity.Dataset;

public record NewSubmission(Contract contract, Dataset dataset) {
}
