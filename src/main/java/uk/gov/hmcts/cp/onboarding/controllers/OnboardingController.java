package uk.gov.hmcts.cp.onboarding.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.cp.onboarding.services.OnboardingService;
import uk.gov.hmcts.cp.openapi.api.OnboardingApi;
import uk.gov.hmcts.cp.openapi.model.OnboardingRequest;
import uk.gov.hmcts.cp.openapi.model.OnboardingRequestResponse;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/onboarding")
public class OnboardingController implements OnboardingApi {

    private final OnboardingService onboardingService;

    @Override
    public ResponseEntity<OnboardingRequestResponse> createOnboardingRequest(final OnboardingRequest request) {
        log.info("Onboarding request received: organisation={}, apiRequested={}, environment={}",
                request.getOrganisation(),
                request.getApiRequested(),
                request.getEnvironment());

        final OnboardingRequestResponse response = onboardingService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/requests")
    public ResponseEntity<List<OnboardingRequestResponse>> getAllRequests() {
        log.info("Get all onboarding requests");
        return ResponseEntity.ok(List.of());
    }

    @DeleteMapping("/requests/{requestId}")
    public ResponseEntity<Void> deleteRequest(final @PathVariable String requestId) {
        log.info("Delete onboarding request: requestId={}", requestId);
        return ResponseEntity.noContent().build();
    }
}
