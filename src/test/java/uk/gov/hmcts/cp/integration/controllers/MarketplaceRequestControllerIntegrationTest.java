package uk.gov.hmcts.cp.integration.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import uk.gov.hmcts.cp.integration.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MarketplaceRequestControllerIntegrationTest extends IntegrationTestBase {

    @BeforeEach
    void beforeEach() {
        clearAllTables();
    }

    @Test
    void posting_onboarding_request_should_persist_and_return_201() throws Exception {
        mockMvc.perform(post("/v1/onboarding")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"John Doe","organisation":"HMCTS","email":"x@a.com",
                                 "jobTitle":"Dev","apiRequested":"RAG Service API",
                                 "environment":"sandbox","useCase":"Testing"}
                                """))
                .andExpect(status().isCreated());

        assertThat(marketplaceRequestRepository.count()).isEqualTo(1);
    }

    @Test
    void posting_publish_request_should_persist_and_return_201() throws Exception {
        mockMvc.perform(post("/v1/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"organisation":"HMCTS","email":"x@a.com","jobTitle":"Dev",
                                 "apiName":"RAG Service API","repoName":"my-repo","version":"1.0.0",
                                 "domain":"Hearing Results","classification":"public","description":"A test API"}
                                """))
                .andExpect(status().isCreated());

        assertThat(marketplaceRequestRepository.count()).isEqualTo(1);
    }

    @Test
    void posting_new_api_request_should_persist_and_return_201() throws Exception {
        mockMvc.perform(post("/v1/requests/new-api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"organisation":"HMCTS","email":"x@a.com","jobTitle":"Architect",
                                 "domain":"Hearing Results","need":"Access to case data",
                                 "urgency":"High","existingWorkaround":"None"}
                                """))
                .andExpect(status().isCreated());

        assertThat(marketplaceRequestRepository.count()).isEqualTo(1);
    }

    @Test
    void posting_contact_request_should_persist_and_return_201() throws Exception {
        mockMvc.perform(post("/v1/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"organisation":"HMCTS","email":"x@a.com",
                                 "topic":"API Access","message":"I would like access to the Hearing Results API"}
                                """))
                .andExpect(status().isCreated());

        assertThat(marketplaceRequestRepository.count()).isEqualTo(1);
    }
}
