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
@Table(name = "marketplace_request")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketplaceRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String type;
    private String payload;
    private String status;
    private OffsetDateTime submittedAt;
}
