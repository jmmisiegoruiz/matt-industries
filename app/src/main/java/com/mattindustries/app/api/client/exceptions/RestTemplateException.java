package com.mattindustries.app.api.client.exceptions;

import com.mattindustries.app.api.client.model.DownstreamApi;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper=false)
public class RestTemplateException extends RuntimeException {

    private DownstreamApi api;
    private HttpStatus statusCode;
    private String error;

    public RestTemplateException(DownstreamApi api, HttpStatus statusCode, String error) {
        super(error);
        this.api = api;
        this.statusCode = statusCode;
        this.error = error;
    }
}
