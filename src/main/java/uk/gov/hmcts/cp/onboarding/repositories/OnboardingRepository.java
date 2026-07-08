package uk.gov.hmcts.cp.onboarding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.hmcts.cp.onboarding.entities.OnboardingEntity;

import java.util.UUID;

@Repository
public interface OnboardingRepository extends JpaRepository<OnboardingEntity, UUID> {
}
