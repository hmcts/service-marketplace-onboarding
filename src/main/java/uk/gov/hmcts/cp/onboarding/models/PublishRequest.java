package uk.gov.hmcts.cp.onboarding.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublishRequest {

    private String fullName;
    private String organisation;
    private String email;
    private String jobTitle;
    private String phone;
    private String apiName;
    private String repoName;
    private String version;
    private String domain;
    private String classification;
    private String description;
}
