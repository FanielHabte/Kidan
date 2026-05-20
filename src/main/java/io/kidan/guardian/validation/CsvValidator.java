package io.kidan.guardian.validation;

import io.kidan.guardian.dto.csv.CsvFormWrapper;
import org.springframework.stereotype.Component;

@Component
public class CsvValidator implements GuardianValidator<CsvFormWrapper> {

    @Override
    public boolean fileValidation(CsvFormWrapper csvFormWrapper) {
        return !csvFormWrapper.getCsvRuleFormList().isEmpty();
    }

}
