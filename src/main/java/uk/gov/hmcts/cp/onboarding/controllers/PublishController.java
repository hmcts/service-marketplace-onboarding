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
import uk.gov.hmcts.cp.onboarding.models.PublishRequest;
import uk.gov.hmcts.cp.onboarding.services.MarketplaceRequestService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/requests")
public class PublishController {

    private final MarketplaceRequestService marketplaceRequestService;
    private final Gson gson;

    @PostMapping("/publish")
    public ResponseEntity<Void> publishApi(final @RequestBody PublishRequest request) {
        log.info("API publish request received: organisation={}, apiName={}", Encode.forJava(request.getOrganisation()), Encode.forJava(request.getApiName()));
        marketplaceRequestService.save("publish", gson.toJson(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
