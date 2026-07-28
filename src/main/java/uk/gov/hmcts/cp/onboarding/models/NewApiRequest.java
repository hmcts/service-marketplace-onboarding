package uk.gov.hmcts.cp.onboarding.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewApiRequest {

    private String fullName;
    private String organisation;
    private String email;
    private String jobTitle;
    private String phone;
    private String need;
    private String domain;
    private String urgency;
    private String existingWorkaround;
}
