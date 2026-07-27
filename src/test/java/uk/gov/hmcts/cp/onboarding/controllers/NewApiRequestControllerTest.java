package uk.gov.hmcts.cp.onboarding.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.hmcts.cp.onboarding.models.NewApiRequest;
import uk.gov.hmcts.cp.onboarding.services.NewApiRequestService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class NewApiRequestControllerTest {

    @Mock
    private NewApiRequestService newApiRequestService;

    @InjectMocks
    private NewApiRequestController controller;

    @Mock
    private NewApiRequest request;

    @Test
    void requesting_new_api_should_return_201() {
        final ResponseEntity<Void> response = controller.requestNewApi(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
