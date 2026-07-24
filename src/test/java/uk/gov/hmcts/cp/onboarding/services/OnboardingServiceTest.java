package uk.gov.hmcts.cp.onboarding.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.hmcts.cp.onboarding.entities.OnboardingEntity;
import uk.gov.hmcts.cp.onboarding.mappers.OnboardingMapper;
import uk.gov.hmcts.cp.onboarding.repositories.OnboardingRepository;
import uk.gov.hmcts.cp.openapi.model.OnboardingRequest;
import uk.gov.hmcts.cp.openapi.model.OnboardingRequestResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OnboardingServiceTest {

    @Mock
    private OnboardingRepository onboardingRepository;

    @Mock
    private OnboardingMapper onboardingMapper;

    @InjectMocks
    private OnboardingService onboardingService;

    @Mock
    private OnboardingRequest request;

    @Mock
    private OnboardingEntity entity;

    @Mock
    private OnboardingEntity saved;

    @Mock
    private OnboardingRequestResponse expectedResponse;

    @Test
    void saving_request_should_delegate_to_mapper_and_repository() {
        when(onboardingMapper.toEntity(request)).thenReturn(entity);
        when(onboardingRepository.save(entity)).thenReturn(saved);
        when(onboardingMapper.toResponse(saved)).thenReturn(expectedResponse);

        final OnboardingRequestResponse response = onboardingService.save(request);

        assertThat(response).isEqualTo(expectedResponse);
    }
}
