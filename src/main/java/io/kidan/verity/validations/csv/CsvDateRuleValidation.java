package io.kidan.verity.validations.csv;

import io.kidan.guardian.dto.csv.response.CsvContractRule;
import io.kidan.guardian.dto.csv.response.DateContractRule;

public class CsvDateRuleValidation implements CsvFileValidation {

    @Override
    public String buildValidationQuery(CsvContractRule csvRule) {
        DateContractRule dateRule = (DateContractRule) csvRule;
        String strpTime = switch (dateRule.getFormatType()) {
            case ("MM_DD_YYYY") -> "%m/%d/%y";
            case ("DD_MM_YYYY") -> "%d/%m/%y";
            case ("YYYY_MM_DD") -> "%y/%m/%d";
            case ("YYYY_MM_DD_HH_MM_SS") -> "%Y/%m/%d %H:%M:%S";
            case ("YYYY_MM_DD_HH_MM_SS_Z") -> "%Y/%m/%d %H:%M:%S Z";
            default -> throw new RuntimeException("Invalid format: " + dateRule.getFormatType() +
                    " please include it in the switch statement.");
        };

        return "try_strptime(" + dateRule.getColumnName() + ", '" + strpTime + "')";
    }

}
