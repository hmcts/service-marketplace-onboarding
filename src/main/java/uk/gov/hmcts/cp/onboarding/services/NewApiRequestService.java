package uk.gov.hmcts.cp.onboarding.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.cp.onboarding.mappers.NewApiRequestMapper;
import uk.gov.hmcts.cp.onboarding.models.NewApiRequest;
import uk.gov.hmcts.cp.onboarding.repositories.NewApiRequestRepository;

@Service
@RequiredArgsConstructor
public class NewApiRequestService {

    private final NewApiRequestRepository newApiRequestRepository;
    private final NewApiRequestMapper newApiRequestMapper;

    public void save(final NewApiRequest request) {
        newApiRequestRepository.save(newApiRequestMapper.toEntity(request));
    }
}
