package uk.gov.hmcts.cp.onboarding.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.cp.onboarding.entities.OnboardingEntity;
import uk.gov.hmcts.cp.onboarding.mappers.OnboardingMapper;
import uk.gov.hmcts.cp.onboarding.repositories.OnboardingRepository;
import uk.gov.hmcts.cp.openapi.model.OnboardingRequest;
import uk.gov.hmcts.cp.openapi.model.OnboardingRequestResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnboardingService {

    private final OnboardingRepository onboardingRequestRepository;
    private final OnboardingMapper onboardingRequestMapper;

    public OnboardingRequestResponse save(final OnboardingRequest request) {
        final OnboardingEntity entity = onboardingRequestMapper.toEntity(request);
        final OnboardingEntity saved = onboardingRequestRepository.save(entity);

        log.info("Onboarding request persisted: id={}, organisation={}", saved.getId(), saved.getOrganisation());

        return onboardingRequestMapper.toResponse(saved);
    }
}
