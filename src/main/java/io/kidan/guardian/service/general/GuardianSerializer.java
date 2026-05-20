package io.kidan.guardian.service.general;


public interface GuardianSerializer <T> {

    String getRuleConfig (T formInputObject);

}