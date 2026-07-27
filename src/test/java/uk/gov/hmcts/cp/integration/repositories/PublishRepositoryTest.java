package uk.gov.hmcts.cp.integration.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.hmcts.cp.integration.IntegrationTestBase;
import uk.gov.hmcts.cp.onboarding.entities.PublishEntity;
import uk.gov.hmcts.cp.onboarding.repositories.PublishRepository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PublishRepositoryTest extends IntegrationTestBase {

    @Autowired
    private PublishRepository publishRepository;

    @BeforeEach
    void beforeEach() {
        clearAllTables();
    }

    @Test
    void saving_publish_request_should_persist_and_return_generated_id() {
        final PublishEntity saved = publishRepository.save(buildEntity());

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getOrganisation()).isEqualTo("HMCTS");
        assertThat(saved.getApiName()).isEqualTo("RAG Service API");
        assertThat(saved.getStatus()).isEqualTo("pending");
        assertThat(saved.getSubmittedAt()).isNotNull();
    }

    @Test
    void finding_by_id_should_return_entity_with_all_fields() {
        final PublishEntity saved = publishRepository.save(buildEntity());

        final Optional<PublishEntity> found = publishRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getDomain()).isEqualTo("Hearing Results");
        assertThat(found.get().getClassification()).isEqualTo("public");
        assertThat(found.get().getRepoName()).isEqualTo("my-repo");
        assertThat(found.get().getVersion()).isEqualTo("1.0.0");
    }

    private static PublishEntity buildEntity() {
        return PublishEntity.builder()
                .organisation("HMCTS")
                .email("x@a.com")
                .jobTitle("Dev")
                .apiName("RAG Service API")
                .repoName("my-repo")
                .version("1.0.0")
                .domain("Hearing Results")
                .classification("public")
                .description("A test API")
                .status("pending")
                .submittedAt(OffsetDateTime.now(ZoneOffset.UTC))
                .build();
    }
}
