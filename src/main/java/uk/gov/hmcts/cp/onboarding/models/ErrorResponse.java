package uk.gov.hmcts.cp.onboarding.models;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class ErrorResponse {

    private String message;
    private Instant timestamp;
    private String traceId;
}
