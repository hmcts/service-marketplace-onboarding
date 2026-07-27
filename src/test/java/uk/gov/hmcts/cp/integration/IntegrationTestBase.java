package uk.gov.hmcts.cp.integration;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.hmcts.cp.integration.config.TestContainersInitialise;
import uk.gov.hmcts.cp.onboarding.repositories.ContactRepository;
import uk.gov.hmcts.cp.onboarding.repositories.NewApiRequestRepository;
import uk.gov.hmcts.cp.onboarding.repositories.OnboardingRepository;
import uk.gov.hmcts.cp.onboarding.repositories.PublishRepository;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(initializers = TestContainersInitialise.class)
public abstract class IntegrationTestBase {

    @Resource
    protected MockMvc mockMvc;

    @Autowired
    protected OnboardingRepository onboardingRepository;

    @Autowired
    protected PublishRepository publishRepository;

    @Autowired
    protected NewApiRequestRepository newApiRequestRepository;

    @Autowired
    protected ContactRepository contactRepository;

    protected void clearAllTables() {
        log.info("Clearing all tables");
        onboardingRepository.deleteAll();
        publishRepository.deleteAll();
        newApiRequestRepository.deleteAll();
        contactRepository.deleteAll();
    }
}
