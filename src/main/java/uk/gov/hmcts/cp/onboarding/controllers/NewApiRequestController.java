package uk.gov.hmcts.cp.onboarding.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.cp.onboarding.models.NewApiRequest;
import uk.gov.hmcts.cp.onboarding.services.NewApiRequestService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/requests")
public class NewApiRequestController {

    private final NewApiRequestService newApiRequestService;

    @PostMapping("/new-api")
    public ResponseEntity<Void> requestNewApi(final @RequestBody NewApiRequest request) {
        log.info("New API request received: organisation={}, domain={}", request.getOrganisation(), request.getDomain());
        newApiRequestService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
