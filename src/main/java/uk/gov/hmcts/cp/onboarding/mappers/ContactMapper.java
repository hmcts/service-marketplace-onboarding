package uk.gov.hmcts.cp.onboarding.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.cp.onboarding.entities.ContactEntity;
import uk.gov.hmcts.cp.onboarding.models.ContactRequest;
import uk.gov.hmcts.cp.onboarding.services.ClockService;

@Component
@RequiredArgsConstructor
public class ContactMapper {

    private final ClockService clockService;

    public ContactEntity toEntity(final ContactRequest request) {
        return ContactEntity.builder()
                .organisation(request.getOrganisation())
                .email(request.getEmail())
                .topic(request.getTopic())
                .message(request.getMessage())
                .status("pending")
                .submittedAt(clockService.nowOffsetUTC())
                .build();
    }
}
