package uk.gov.hmcts.cp.onboarding.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.hmcts.cp.onboarding.services.OnboardingService;
import uk.gov.hmcts.cp.openapi.model.OnboardingRequest;
import uk.gov.hmcts.cp.openapi.model.OnboardingRequestResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OnboardingControllerTest {

    @Mock
    private OnboardingService onboardingService;

    @InjectMocks
    private OnboardingController controller;

    @Mock
    private OnboardingRequest request;

    @Mock
    private OnboardingRequestResponse serviceResponse;

    @Test
    void creating_onboarding_request_should_return_201_with_service_response() {
        when(onboardingService.save(request)).thenReturn(serviceResponse);

        final ResponseEntity<OnboardingRequestResponse> response = controller.createOnboardingRequest(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(serviceResponse);
    }
}
