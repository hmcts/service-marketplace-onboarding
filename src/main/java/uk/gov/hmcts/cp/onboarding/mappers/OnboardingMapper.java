package uk.gov.hmcts.cp.onboarding.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.cp.onboarding.entities.OnboardingEntity;
import uk.gov.hmcts.cp.onboarding.services.ClockService;
import uk.gov.hmcts.cp.openapi.model.OnboardingRequest;
import uk.gov.hmcts.cp.openapi.model.OnboardingRequestResponse;

@Component
@RequiredArgsConstructor
public class OnboardingMapper {

    private final ClockService clockService;

    public OnboardingEntity toEntity(final OnboardingRequest request) {
        return OnboardingEntity.builder()
                .name(request.getName())
                .organisation(request.getOrganisation())
                .email(request.getEmail())
                .jobTitle(request.getJobTitle())
                .phone(request.getPhone())
                .apiRequested(request.getApiRequested())
                .environment(request.getEnvironment())
                .callVolume(request.getCallVolume())
                .useCase(request.getUseCase())
                .status("pending")
                .submittedAt(clockService.nowOffsetUTC())
                .build();
    }

    public OnboardingRequestResponse toResponse(final OnboardingEntity entity) {
        return OnboardingRequestResponse.builder()
                .requestId(entity.getId().toString())
                .status(entity.getStatus())
                .submittedAt(entity.getSubmittedAt().toInstant())
                .build();
    }
}
