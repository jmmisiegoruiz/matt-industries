package com.mattindustries.app.model;

import com.mattindustries.app.api.client.exceptions.RestTemplateException;
import com.mattindustries.app.api.client.model.DownstreamApi;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ErrorResponse {

    private String timestamp;

    /** HTTP Status Code */
    private int status;

    /** HTTP Reason phrase */
    private String error;

    /** A message that describe the error thrown when calling the downstream API */
    private String message;

    /** Downstream API name that has been called by this application */
    private DownstreamApi api;

    /** URI that has been called */
    private String path;

    public ErrorResponse(RestTemplateException ex, String requestURI) {
        this.timestamp = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        this.api = ex.getApi();
        this.status = ex.getStatusCode().value();
        this.error = ex.getStatusCode().getReasonPhrase();
        this.message = ex.getError();
        this.path = requestURI;
    }
}
