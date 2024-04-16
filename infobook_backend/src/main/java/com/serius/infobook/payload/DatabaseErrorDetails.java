package com.serius.infobook.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class DatabaseErrorDetails {
    private Date date;
    private String message;
    private int errorCode;
    private Throwable cause;
}
