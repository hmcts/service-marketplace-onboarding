package uk.gov.hmcts.cp.onboarding.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingRequest {

    private String name;
    private String organisation;
    private String email;
    private String jobTitle;
    private String phone;
    private String apiRequested;
    private String environment;
    private String callVolume;
    private String useCase;
}
