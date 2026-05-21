package io.kidan.guardian.service.serializer;

import com.google.gson.Gson;
import io.kidan.guardian.dto.csv.CsvFileStructure;
import io.kidan.guardian.dto.csv.CsvRuleForm;
import io.kidan.guardian.service.special.GuardianSerializer;
import org.springframework.stereotype.Component;

@Component
public class CsvSerializer implements GuardianSerializer<CsvRuleForm, CsvFileStructure> {


    @Override
    public String getRuleConfig(CsvRuleForm csvRuleForm) {
        Gson gson = new Gson();
        return gson.toJson(csvRuleForm);
    }

    @Override
    public String getContractConfig(CsvFileStructure csvFileStructure) {
        CsvFileStructure formInputObject = csvFileStructure;
        Gson gson = new Gson();
        formInputObject.setContractName(null);
        formInputObject.setDescription(null);
        return gson.toJson(formInputObject);
    }


}
