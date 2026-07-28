package uk.gov.hmcts.cp.onboarding.controllers;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.hmcts.cp.onboarding.models.PublishRequest;
import uk.gov.hmcts.cp.onboarding.services.MarketplaceRequestService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PublishControllerTest {

    @Mock
    private MarketplaceRequestService marketplaceRequestService;

    @Spy
    private Gson gson = new Gson();

    @InjectMocks
    private PublishController controller;

    @Mock
    private PublishRequest request;

    @Test
    void publishing_api_should_return_201() {
        final ResponseEntity<Void> response = controller.publishApi(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
