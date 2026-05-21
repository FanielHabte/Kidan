package io.kidan.guardian.service.special;


public interface GuardianSerializer<T, X> {

    String getRuleConfig(T formInputObject);
    String getContractConfig(X formInputObject);
}