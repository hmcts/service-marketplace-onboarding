package uk.gov.hmcts.cp.onboarding;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.cp.openapi.api.OnboardingApi;
import uk.gov.hmcts.cp.openapi.model.OnboardingRequest;
import uk.gov.hmcts.cp.openapi.model.OnboardingRequestResponse;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@RestController
public class OnboardingController implements OnboardingApi {

    // TODO: replace with DB persistence (amp-816)
    private final List<OnboardingRequest> store = new CopyOnWriteArrayList<>();

    @Override
    public ResponseEntity<OnboardingRequestResponse> createOnboardingRequest(final OnboardingRequest request) {
        log.info("Onboarding request received: name={}, organisation={}, email={}, apiRequested={}, environment={}, useCase={}",
            request.getName(),
            request.getOrganisation(),
            request.getEmail(),
            request.getApiRequested(),
            request.getEnvironment(),
            request.getUseCase());

        store.add(request);

        final OnboardingRequestResponse response = OnboardingRequestResponse.builder()
            .requestId("onb-" + UUID.randomUUID())
            .status("pending")
            .submittedAt(Instant.now())
            .build();

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/onboarding/requests/all")
    public ResponseEntity<List<OnboardingRequest>> getOnboardingRequests() {
        return ResponseEntity.ok(store);
    }
}
