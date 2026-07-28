package uk.gov.hmcts.cp.onboarding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.hmcts.cp.onboarding.entities.MarketplaceRequestEntity;

import java.util.UUID;

public interface MarketplaceRequestRepository extends JpaRepository<MarketplaceRequestEntity, UUID> {
}
