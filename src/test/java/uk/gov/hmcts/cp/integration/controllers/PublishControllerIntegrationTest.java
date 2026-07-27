package uk.gov.hmcts.cp.integration.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import uk.gov.hmcts.cp.integration.IntegrationTestBase;
import uk.gov.hmcts.cp.onboarding.repositories.PublishRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PublishControllerIntegrationTest extends IntegrationTestBase {

    @Autowired
    private PublishRepository publishRepository;

    @BeforeEach
    void beforeEach() {
        clearAllTables();
    }

    @Test
    void posting_publish_request_should_persist_and_return_201() throws Exception {
        mockMvc.perform(post("/api/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "organisation": "HMCTS",
                                  "email": "x@a.com",
                                  "jobTitle": "Dev",
                                  "apiName": "RAG Service API",
                                  "repoName": "my-repo",
                                  "version": "1.0.0",
                                  "domain": "Hearing Results",
                                  "classification": "public",
                                  "description": "A test API"
                                }
                                """))
                .andExpect(status().isCreated());

        assertThat(publishRepository.count()).isEqualTo(1);
    }
}
