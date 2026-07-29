package uk.gov.hmcts.cp.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import uk.gov.hmcts.cp.onboarding.entities.MarketplaceRequestEntity;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MarketplaceRequestIntegrationTest extends IntegrationTestBase {

    @BeforeEach
    void beforeEach() {
        clearAllTables();
    }

    @Test
    void posting_onboarding_request_should_persist_and_return_201() throws Exception {
        mockMvc.perform(post("/v1/requests/onboarding")
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
        mockMvc.perform(post("/v1/requests/publish")
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
        mockMvc.perform(post("/v1/requests/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"organisation":"HMCTS","email":"x@a.com",
                                 "topic":"API Access","message":"I would like access to the Hearing Results API"}
                                """))
                .andExpect(status().isCreated());

        assertThat(marketplaceRequestRepository.count()).isEqualTo(1);
    }

    @Test
    void listing_all_requests_should_return_200_with_stored_requests() throws Exception {
        marketplaceRequestRepository.save(MarketplaceRequestEntity.builder()
                .type("onboarding")
                .payload("{\"organisation\":\"HMCTS\"}")
                .status("pending")
                .submittedAt(OffsetDateTime.now(ZoneOffset.UTC))
                .build());

        mockMvc.perform(get("/v1/requests"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].type").value("onboarding"))
                .andExpect(jsonPath("$[0].status").value("pending"))
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    void deleting_existing_request_should_return_204_and_remove_from_db() throws Exception {
        final MarketplaceRequestEntity saved = marketplaceRequestRepository.save(MarketplaceRequestEntity.builder()
                .type("contact")
                .payload("{\"organisation\":\"HMCTS\"}")
                .status("pending")
                .submittedAt(OffsetDateTime.now(ZoneOffset.UTC))
                .build());

        mockMvc.perform(delete("/v1/requests/" + saved.getId()))
                .andExpect(status().isNoContent());

        assertThat(marketplaceRequestRepository.count()).isZero();
    }

    @Test
    void deleting_nonexistent_request_should_return_404() throws Exception {
        mockMvc.perform(delete("/v1/requests/00000000-0000-0000-0000-000000000000"))
                .andExpect(status().isNotFound());
    }
}
