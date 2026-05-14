package io.kidan.guardian.validation;

public interface GuardianValidator <T> {

    boolean fileValidation (T formInputObject);

}
