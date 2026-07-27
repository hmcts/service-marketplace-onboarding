package uk.gov.hmcts.cp.integration.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import uk.gov.hmcts.cp.integration.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ContactControllerIntegrationTest extends IntegrationTestBase {

    @BeforeEach
    void beforeEach() {
        clearAllTables();
    }

    @Test
    void posting_contact_request_should_persist_and_return_201() throws Exception {
        mockMvc.perform(post("/api/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "organisation": "HMCTS",
                                  "email": "x@a.com",
                                  "topic": "API Access",
                                  "message": "I would like access to the Hearing Results API"
                                }
                                """))
                .andExpect(status().isCreated());

        assertThat(contactRepository.count()).isEqualTo(1);
    }
}
