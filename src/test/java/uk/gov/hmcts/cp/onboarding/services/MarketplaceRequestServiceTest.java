package uk.gov.hmcts.cp.onboarding.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.hmcts.cp.onboarding.entities.MarketplaceRequestEntity;
import uk.gov.hmcts.cp.onboarding.repositories.MarketplaceRequestRepository;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MarketplaceRequestServiceTest {

    static final Instant FIXED_NOW = Instant.parse("2026-01-01T10:00:00Z");

    @Mock
    private MarketplaceRequestRepository marketplaceRequestRepository;

    @Spy
    private ClockService clockService = new ClockService(Clock.fixed(FIXED_NOW, ZoneOffset.UTC));

    @InjectMocks
    private MarketplaceRequestService marketplaceRequestService;

    @Test
    void saving_request_should_persist_with_type_payload_and_pending_status() {
        marketplaceRequestService.save("onboarding", "{\"organisation\":\"HMCTS\"}");

        verify(marketplaceRequestRepository).save(argThat((MarketplaceRequestEntity e) ->
                e.getType().equals("onboarding")
                && e.getPayload().equals("{\"organisation\":\"HMCTS\"}")
                && e.getStatus().equals("pending")));
    }
}
