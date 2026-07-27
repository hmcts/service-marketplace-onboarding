package uk.gov.hmcts.cp.onboarding.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.hmcts.cp.onboarding.models.ContactRequest;
import uk.gov.hmcts.cp.onboarding.services.ContactService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ContactControllerTest {

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController controller;

    @Mock
    private ContactRequest request;

    @Test
    void posting_contact_request_should_return_201() {
        final ResponseEntity<Void> response = controller.contact(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
