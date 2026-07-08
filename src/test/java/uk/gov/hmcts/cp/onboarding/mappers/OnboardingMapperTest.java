package uk.gov.hmcts.cp.onboarding.mappers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.hmcts.cp.onboarding.entities.OnboardingEntity;
import uk.gov.hmcts.cp.onboarding.services.ClockService;
import uk.gov.hmcts.cp.openapi.model.OnboardingRequest;
import uk.gov.hmcts.cp.openapi.model.OnboardingRequestResponse;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OnboardingMapperTest {

    static final UUID ENTITY_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");
    static final Instant FIXED_NOW = Instant.parse("2026-01-01T10:00:00Z");

    @Spy
    private ClockService clockService = new ClockService(Clock.fixed(FIXED_NOW, ZoneOffset.UTC));

    @InjectMocks
    private OnboardingMapper mapper;

    @Test
    void mapping_request_to_entity_should_set_all_fields() {
        final OnboardingEntity entity = mapper.toEntity(buildRequest());

        assertThat(entity.getId()).isNull();
        assertThat(entity.getName()).isEqualTo("John Doe");
        assertThat(entity.getOrganisation()).isEqualTo("HMCTS");
        assertThat(entity.getEmail()).isEqualTo("x@a.com");
        assertThat(entity.getJobTitle()).isEqualTo("Dev");
        assertThat(entity.getPhone()).isEqualTo("+447973254324");
        assertThat(entity.getApiRequested()).isEqualTo("RAG Service API");
        assertThat(entity.getEnvironment()).isEqualTo("sandbox");
        assertThat(entity.getCallVolume()).isEqualTo("low");
        assertThat(entity.getUseCase()).isEqualTo("testing");
        assertThat(entity.getStatus()).isEqualTo("pending");
        assertThat(entity.getSubmittedAt()).isEqualTo(OffsetDateTime.ofInstant(FIXED_NOW, ZoneOffset.UTC));
    }

    @Test
    void mapping_entity_to_response_should_set_all_fields() {
        final OnboardingRequestResponse response = mapper.toResponse(buildSavedEntity());

        assertThat(response.getRequestId()).isEqualTo(ENTITY_ID.toString());
        assertThat(response.getStatus()).isEqualTo("pending");
        assertThat(response.getSubmittedAt()).isEqualTo(FIXED_NOW);
    }

    private static OnboardingRequest buildRequest() {
        return OnboardingRequest.builder()
                .name("John Doe")
                .organisation("HMCTS")
                .email("x@a.com")
                .jobTitle("Dev")
                .phone("+447973254324")
                .apiRequested("RAG Service API")
                .environment("sandbox")
                .callVolume("low")
                .useCase("testing")
                .build();
    }

    private static OnboardingEntity buildSavedEntity() {
        return OnboardingEntity.builder()
                .id(ENTITY_ID)
                .name("John Doe")
                .organisation("HMCTS")
                .email("x@a.com")
                .jobTitle("Dev")
                .phone("+447973254324")
                .apiRequested("RAG Service API")
                .environment("sandbox")
                .callVolume("low")
                .useCase("testing")
                .status("pending")
                .submittedAt(OffsetDateTime.ofInstant(FIXED_NOW, ZoneOffset.UTC))
                .build();
    }
}
