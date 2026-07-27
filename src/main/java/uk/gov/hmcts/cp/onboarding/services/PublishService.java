package uk.gov.hmcts.cp.onboarding.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.cp.onboarding.mappers.PublishMapper;
import uk.gov.hmcts.cp.onboarding.models.PublishRequest;
import uk.gov.hmcts.cp.onboarding.repositories.PublishRepository;

@Service
@RequiredArgsConstructor
public class PublishService {

    private final PublishRepository publishRepository;
    private final PublishMapper publishMapper;

    public void save(final PublishRequest request) {
        publishRepository.save(publishMapper.toEntity(request));
    }
}
