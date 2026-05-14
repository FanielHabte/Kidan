package io.kidan.guardian.web.serlization;


public interface GuardianSerializer <T> {

    String getRuleConfig (T formInputObject);

}