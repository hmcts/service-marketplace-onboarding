package uk.gov.hmcts.cp.onboarding.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.cp.onboarding.mappers.ContactMapper;
import uk.gov.hmcts.cp.onboarding.models.ContactRequest;
import uk.gov.hmcts.cp.onboarding.repositories.ContactRepository;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public void save(final ContactRequest request) {
        contactRepository.save(contactMapper.toEntity(request));
    }
}
