package uk.gov.hmcts.cp.onboarding.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.cp.onboarding.entities.MarketplaceRequestEntity;
import uk.gov.hmcts.cp.onboarding.repositories.MarketplaceRequestRepository;

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
}
