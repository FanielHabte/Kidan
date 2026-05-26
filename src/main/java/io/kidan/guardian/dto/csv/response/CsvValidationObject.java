package io.kidan.guardian.dto.csv.response;

import io.kidan.guardian.enums.RuleType;

public record CsvValidationObject(String columnName, boolean isRequired,
                                  boolean isUnique, RuleType ruleType,
                                  String ruleConfig)
{}
