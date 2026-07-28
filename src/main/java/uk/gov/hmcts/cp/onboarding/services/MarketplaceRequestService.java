package uk.gov.hmcts.cp.onboarding.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uk.gov.hmcts.cp.onboarding.entities.MarketplaceRequestEntity;
import uk.gov.hmcts.cp.onboarding.models.MarketplaceRequestResponse;
import uk.gov.hmcts.cp.onboarding.repositories.MarketplaceRequestRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarketplaceRequestService {

    private final MarketplaceRequestRepository marketplaceRequestRepository;
    private final ClockService clockService;

    public void save(final String type, final String payload) {
        marketplaceRequestRepository.save(MarketplaceRequestEntity.builder()
                .type(type)
                .payload(payload)
                .status("pending")
                .submittedAt(clockService.nowOffsetUTC())
                .build());
    }

    public List<MarketplaceRequestResponse> findAll() {
        return marketplaceRequestRepository.findAll().stream()
                .map(e -> MarketplaceRequestResponse.builder()
                        .id(e.getId())
                        .type(e.getType())
                        .payload(e.getPayload())
                        .status(e.getStatus())
                        .submittedAt(e.getSubmittedAt())
                        .build())
                .toList();
    }

    public void deleteById(final UUID id) {
        if (!marketplaceRequestRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found: " + id);
        }
        marketplaceRequestRepository.deleteById(id);
    }
}
