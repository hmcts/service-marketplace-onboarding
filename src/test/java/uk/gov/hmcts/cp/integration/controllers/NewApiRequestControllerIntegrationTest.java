package uk.gov.hmcts.cp.integration.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import uk.gov.hmcts.cp.integration.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class NewApiRequestControllerIntegrationTest extends IntegrationTestBase {

    @BeforeEach
    void beforeEach() {
        clearAllTables();
    }

    @Test
    void posting_new_api_request_should_persist_and_return_201() throws Exception {
        mockMvc.perform(post("/api/requests/new-api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "organisation": "HMCTS",
                                  "email": "x@a.com",
                                  "jobTitle": "Architect",
                                  "domain": "Hearing Results",
                                  "need": "Access to case data",
                                  "urgency": "High",
                                  "existingWorkaround": "None"
                                }
                                """))
                .andExpect(status().isCreated());

        assertThat(newApiRequestRepository.count()).isEqualTo(1);
    }
}
