package uk.gov.hmcts.cp.onboarding.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.hmcts.cp.onboarding.entities.PublishEntity;
import uk.gov.hmcts.cp.onboarding.mappers.PublishMapper;
import uk.gov.hmcts.cp.onboarding.models.PublishRequest;
import uk.gov.hmcts.cp.onboarding.repositories.PublishRepository;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PublishServiceTest {

    @Mock
    private PublishRepository publishRepository;

    @Mock
    private PublishMapper publishMapper;

    @InjectMocks
    private PublishService publishService;

    @Mock
    private PublishRequest request;

    @Mock
    private PublishEntity entity;

    @Test
    void saving_publish_request_should_delegate_to_mapper_and_repository() {
        when(publishMapper.toEntity(request)).thenReturn(entity);

        publishService.save(request);

        verify(publishRepository).save(entity);
    }
}
