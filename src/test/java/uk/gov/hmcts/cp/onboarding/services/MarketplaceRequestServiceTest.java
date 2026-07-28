package uk.gov.hmcts.cp.onboarding.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import uk.gov.hmcts.cp.onboarding.entities.MarketplaceRequestEntity;
import uk.gov.hmcts.cp.onboarding.models.MarketplaceRequestResponse;
import uk.gov.hmcts.cp.onboarding.repositories.MarketplaceRequestRepository;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void finding_all_requests_should_return_mapped_responses() {
        final UUID id = UUID.randomUUID();
        final MarketplaceRequestEntity entity = MarketplaceRequestEntity.builder()
                .id(id)
                .type("onboarding")
                .payload("{\"organisation\":\"HMCTS\"}")
                .status("pending")
                .submittedAt(OffsetDateTime.now(ZoneOffset.UTC))
                .build();
        when(marketplaceRequestRepository.findAll()).thenReturn(List.of(entity));

        final List<MarketplaceRequestResponse> result = marketplaceRequestService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(id);
        assertThat(result.get(0).getType()).isEqualTo("onboarding");
        assertThat(result.get(0).getStatus()).isEqualTo("pending");
    }

    @Test
    void deleting_existing_request_should_invoke_repository_delete() {
        final UUID id = UUID.randomUUID();
        when(marketplaceRequestRepository.existsById(id)).thenReturn(true);

        marketplaceRequestService.deleteById(id);

        verify(marketplaceRequestRepository).deleteById(id);
    }

    @Test
    void deleting_nonexistent_request_should_throw_not_found() {
        final UUID id = UUID.randomUUID();
        when(marketplaceRequestRepository.existsById(id)).thenReturn(false);

        assertThatThrownBy(() -> marketplaceRequestService.deleteById(id))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Request not found");
    }
}
