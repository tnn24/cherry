package com.cherry.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    public static final String JSON_KEY_STATUS = "status";
    public static final String JSON_KEY_ERROR_MESSAGE = "message";
    public static final String JSON_KEY_TIMESTAMP = "timestamp";
    public static final String JSON_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm";

    @JsonProperty(JSON_KEY_STATUS)
    private int statusCode;
    @JsonProperty(JSON_KEY_ERROR_MESSAGE)
    private String errorMessage;
    @JsonProperty(JSON_KEY_TIMESTAMP)
    @JsonFormat(pattern = JSON_TIMESTAMP_FORMAT)
    private LocalDateTime timestamp;
}