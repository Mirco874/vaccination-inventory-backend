package com.mirco.employeeControl.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.boot.jaxb.cfg.spi.JaxbCfgConfigPropertyType;

@Getter
@AllArgsConstructor
public enum HttpResponseMessage {
    FIND_SUCCESSFUL("Successful search"),
    PERSIST_SUCCESSFUL("Registered successfully"),
    UPDATE_SUCCESSFUL("Updated correctly"),
    DELETE_SUCCESSFUL("Deleted successfully"),
    NOT_FOUND_RECORD("Record not found"),
    PERSON_RECORD_EXISTENT("The identity document provided is already registered"),
    NOT_FOUND_USER("User not found"),
    FAIL_TO_SAVE("Fail to save"),
    FAIL_TO_UPDATE("The entered record was not updated"),
    PERSIST_FAILED(""),
    NOT_FOUND("The new record was not saved, check the data sent"),
    FAIL_TO_DELETE( "Could not delete record") ,
    DUPLICATE_EMAIL("The email entered is already registered"),
    DUPLICATE_IDENTITY_CARD("the identity document is already registered");

    private final String value;
}
