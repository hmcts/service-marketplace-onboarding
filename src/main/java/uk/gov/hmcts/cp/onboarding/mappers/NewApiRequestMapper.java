package uk.gov.hmcts.cp.onboarding.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.cp.onboarding.entities.NewApiRequestEntity;
import uk.gov.hmcts.cp.onboarding.models.NewApiRequest;
import uk.gov.hmcts.cp.onboarding.services.ClockService;

@Component
@RequiredArgsConstructor
public class NewApiRequestMapper {

    private final ClockService clockService;

    public NewApiRequestEntity toEntity(final NewApiRequest request) {
        return NewApiRequestEntity.builder()
                .organisation(request.getOrganisation())
                .email(request.getEmail())
                .jobTitle(request.getJobTitle())
                .phone(request.getPhone())
                .need(request.getNeed())
                .domain(request.getDomain())
                .urgency(request.getUrgency())
                .existingWorkaround(request.getExistingWorkaround())
                .status("pending")
                .submittedAt(clockService.nowOffsetUTC())
                .build();
    }
}
