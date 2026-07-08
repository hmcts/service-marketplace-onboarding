package uk.gov.hmcts.cp.integration.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.hmcts.cp.integration.IntegrationTestBase;
import uk.gov.hmcts.cp.onboarding.entities.OnboardingEntity;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class OnboardingRepositoryTest extends IntegrationTestBase {

    @BeforeEach
    void beforeEach() {
        clearAllTables();
    }

    @Test
    void saving_onboarding_request_should_persist_and_return_generated_id() {
        final OnboardingEntity entity = buildEntity("John Doe", "HMCTS");

        final OnboardingEntity saved = onboardingRepository.save(entity);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("John Doe");
        assertThat(saved.getOrganisation()).isEqualTo("HMCTS");
    }

    @Test
    void finding_by_id_should_return_entity_with_all_fields() {
        final OnboardingEntity saved = onboardingRepository.save(buildEntity("John Doe", "HMCTS"));

        final Optional<OnboardingEntity> found = onboardingRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("x@a.com");
        assertThat(found.get().getJobTitle()).isEqualTo("Dev");
        assertThat(found.get().getApiRequested()).isEqualTo("RAG Service API");
        assertThat(found.get().getEnvironment()).isEqualTo("sandbox");
        assertThat(found.get().getCallVolume()).isEqualTo("low");
        assertThat(found.get().getUseCase()).isEqualTo("testing");
        assertThat(found.get().getStatus()).isEqualTo("pending");
        assertThat(found.get().getSubmittedAt()).isNotNull();
    }

    @Test
    void nullable_fields_should_persist_as_null() {
        final OnboardingEntity entity = OnboardingEntity.builder()
                .name("John Doe")
                .organisation("HMCTS")
                .email("x@a.com")
                .jobTitle("Dev")
                .phone(null)
                .apiRequested("RAG Service API")
                .environment("sandbox")
                .callVolume(null)
                .useCase("testing")
                .status("pending")
                .submittedAt(OffsetDateTime.now(ZoneOffset.UTC))
                .build();

        final OnboardingEntity saved = onboardingRepository.save(entity);
        final Optional<OnboardingEntity> found = onboardingRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getPhone()).isNull();
        assertThat(found.get().getCallVolume()).isNull();
    }

    private static OnboardingEntity buildEntity(final String name, final String organisation) {
        return OnboardingEntity.builder()
                .name(name)
                .organisation(organisation)
                .email("x@a.com")
                .jobTitle("Dev")
                .phone("+447973254324")
                .apiRequested("RAG Service API")
                .environment("sandbox")
                .callVolume("low")
                .useCase("testing")
                .status("pending")
                .submittedAt(OffsetDateTime.now(ZoneOffset.UTC))
                .build();
    }

}
