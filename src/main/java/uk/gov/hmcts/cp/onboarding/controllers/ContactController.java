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
import uk.gov.hmcts.cp.onboarding.models.ContactRequest;
import uk.gov.hmcts.cp.onboarding.services.MarketplaceRequestService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ContactController {

    private final MarketplaceRequestService marketplaceRequestService;
    private final Gson gson;

    @PostMapping("/contact")
    public ResponseEntity<Void> contact(final @RequestBody ContactRequest request) {
        log.info("Contact request received: organisation={}, topic={}", Encode.forJava(request.getOrganisation()), Encode.forJava(request.getTopic()));
        marketplaceRequestService.save("contact", gson.toJson(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
