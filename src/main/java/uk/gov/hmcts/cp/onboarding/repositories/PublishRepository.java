package uk.gov.hmcts.cp.onboarding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.hmcts.cp.onboarding.entities.PublishEntity;

import java.util.UUID;

@Repository
public interface PublishRepository extends JpaRepository<PublishEntity, UUID> {
}
