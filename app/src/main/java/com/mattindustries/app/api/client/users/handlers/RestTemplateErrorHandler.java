package com.mattindustries.app.api.client.users.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mattindustries.app.api.client.exceptions.RestTemplateException;
import com.mattindustries.app.api.client.model.DownstreamApi;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (httpResponse.getStatusCode().series() == CLIENT_ERROR ||
                httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        HttpStatus httpStatus = httpResponse.getStatusCode();
        if (httpStatus.series() == HttpStatus.Series.SERVER_ERROR) {
            // handle SERVER_ERROR
        } else if (httpStatus.series() == HttpStatus.Series.CLIENT_ERROR) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getBody()))) {
                String httpBodyResponse = reader.lines().collect(Collectors.joining(""));
                VndErrors error = new ObjectMapper().readValue(httpBodyResponse, VndErrors.class);
                // handle CLIENT_ERROR
                if (httpStatus == HttpStatus.NOT_FOUND) {
                    throw new RestTemplateException(DownstreamApi.GET_USER_BY_PRINCIPAL, httpStatus, httpBodyResponse);
                }
            }
        }
    }
}
