package uk.gov.hmcts.cp.onboarding.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.hmcts.cp.onboarding.models.PublishRequest;
import uk.gov.hmcts.cp.onboarding.services.PublishService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PublishControllerTest {

    @Mock
    private PublishService publishService;

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
