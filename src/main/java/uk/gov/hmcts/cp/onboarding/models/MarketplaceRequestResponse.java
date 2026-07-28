package uk.gov.hmcts.cp.onboarding.models;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
public class MarketplaceRequestResponse {

    private UUID id;
    private String type;
    private String payload;
    private String status;
    private OffsetDateTime submittedAt;
}
