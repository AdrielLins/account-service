package org.treasure.accountservice.web.handler;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ErrorResponse(String code,
                            String message,
                            Instant timestamp,
                            List<Detail> errors) {

    @Builder
    public record Detail(String field,
                         String message) {
    }
}
