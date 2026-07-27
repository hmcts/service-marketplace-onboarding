package uk.gov.hmcts.cp.onboarding.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.cp.onboarding.entities.PublishEntity;
import uk.gov.hmcts.cp.onboarding.models.PublishRequest;
import uk.gov.hmcts.cp.onboarding.services.ClockService;

@Component
@RequiredArgsConstructor
public class PublishMapper {

    private final ClockService clockService;

    public PublishEntity toEntity(final PublishRequest request) {
        return PublishEntity.builder()
                .organisation(request.getOrganisation())
                .email(request.getEmail())
                .jobTitle(request.getJobTitle())
                .phone(request.getPhone())
                .apiName(request.getApiName())
                .repoName(request.getRepoName())
                .version(request.getVersion())
                .domain(request.getDomain())
                .classification(request.getClassification())
                .description(request.getDescription())
                .status("pending")
                .submittedAt(clockService.nowOffsetUTC())
                .build();
    }
}
