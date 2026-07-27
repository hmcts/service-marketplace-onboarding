package uk.gov.hmcts.cp.onboarding.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "publish_request")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
    private String status;
    private OffsetDateTime submittedAt;
}
