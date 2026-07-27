package uk.gov.hmcts.cp.onboarding.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.cp.onboarding.models.ContactRequest;
import uk.gov.hmcts.cp.onboarding.services.ContactService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/contact")
    public ResponseEntity<Void> contact(final @RequestBody ContactRequest request) {
        log.info("Contact request received: organisation={}, topic={}", request.getOrganisation(), request.getTopic());
        contactService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
