package com.hybrid.internship.exception.handler;

import java.util.Date;

public class ErrorMessage {
    private final int statusCode;
    private final String message;
    private final Date date;
    private final String description;

    public ErrorMessage(int statusCode, String message, Date date, String description) {
        this.statusCode = statusCode;
        this.message = message;
        this.date = date;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}

