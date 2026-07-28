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
import uk.gov.hmcts.cp.onboarding.models.MarketplaceRequestResponse;
import uk.gov.hmcts.cp.onboarding.services.MarketplaceRequestService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/requests")
public class MarketplaceRequestController {

    private final MarketplaceRequestService marketplaceRequestService;

    @GetMapping
    public ResponseEntity<List<MarketplaceRequestResponse>> listRequests() {
        log.info("Listing all marketplace requests");
        return ResponseEntity.ok(marketplaceRequestService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable final UUID id) {
        log.info("Deleting marketplace request: id={}", id);
        marketplaceRequestService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
