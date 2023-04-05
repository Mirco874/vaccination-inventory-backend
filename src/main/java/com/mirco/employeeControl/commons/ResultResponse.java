package com.mirco.employeeControl.commons;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class ResultResponse {
    @Builder.Default
    private Date timestamp = new Date();
    private boolean status;
    private String message;
    private Object data;
}
