package uk.gov.hmcts.cp.onboarding.controllers;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.owasp.encoder.Encode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.cp.onboarding.models.OnboardingRequest;
import uk.gov.hmcts.cp.onboarding.services.MarketplaceRequestService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/requests")
public class OnboardingController {

    private final MarketplaceRequestService marketplaceRequestService;
    private final Gson gson;

    @PostMapping("/onboarding")
    public ResponseEntity<Void> createOnboardingRequest(final @RequestBody OnboardingRequest request) {
        log.info("Onboarding request received: organisation={}, apiRequested={}, environment={}",
                Encode.forJava(request.getOrganisation()),
                Encode.forJava(request.getApiRequested()),
                Encode.forJava(request.getEnvironment()));
        marketplaceRequestService.save("onboarding", gson.toJson(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
