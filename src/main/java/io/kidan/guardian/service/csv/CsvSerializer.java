package io.kidan.guardian.service.csv;

import com.google.gson.Gson;
import io.kidan.guardian.dto.csv.CsvRuleForm;
import io.kidan.guardian.service.general.GuardianSerializer;
import org.springframework.stereotype.Component;

@Component
public class CsvSerializer implements GuardianSerializer<CsvRuleForm> {

    @Override
    public String getRuleConfig(CsvRuleForm csvRuleForm) {
        Gson gson = new Gson();

        csvRuleForm.setIsRequired(null);
        csvRuleForm.setIsUnique(null);

        return gson.toJson(csvRuleForm);
    }

}
