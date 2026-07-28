package uk.gov.hmcts.cp.onboarding.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.hmcts.cp.onboarding.models.MarketplaceRequestResponse;
import uk.gov.hmcts.cp.onboarding.services.MarketplaceRequestService;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarketplaceRequestControllerTest {

    @Mock
    private MarketplaceRequestService marketplaceRequestService;

    @InjectMocks
    private MarketplaceRequestController controller;

    @Test
    void listing_requests_should_return_200_with_all_requests() {
        final List<MarketplaceRequestResponse> requests = List.of(
                MarketplaceRequestResponse.builder()
                        .id(UUID.randomUUID())
                        .type("onboarding")
                        .payload("{\"organisation\":\"HMCTS\"}")
                        .status("pending")
                        .build()
        );
        when(marketplaceRequestService.findAll()).thenReturn(requests);

        final ResponseEntity<List<MarketplaceRequestResponse>> response = controller.listRequests();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getType()).isEqualTo("onboarding");
    }

    @Test
    void deleting_existing_request_should_return_204() {
        final UUID id = UUID.randomUUID();

        final ResponseEntity<Void> response = controller.deleteRequest(id);

        verify(marketplaceRequestService).deleteById(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
