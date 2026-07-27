package uk.gov.hmcts.cp.onboarding.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest {

    private String fullName;
    private String organisation;
    private String email;
    private String topic;
    private String message;
}
