package io.kidan.guardian.web.serlization;

import com.google.gson.Gson;
import io.kidan.guardian.web.dto.csv.CsvRuleForm;
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
