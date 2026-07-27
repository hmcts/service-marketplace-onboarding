package uk.gov.hmcts.cp.onboarding.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.cp.onboarding.models.PublishRequest;
import uk.gov.hmcts.cp.onboarding.services.PublishService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PublishController {

    private final PublishService publishService;

    @PostMapping("/publish")
    public ResponseEntity<Void> publishApi(final @RequestBody PublishRequest request) {
        log.info("API publish request received: organisation={}, apiName={}", request.getOrganisation(), request.getApiName());
        publishService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
