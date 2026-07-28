package uk.gov.hmcts.cp.integration;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.hmcts.cp.integration.config.TestContainersInitialise;
import uk.gov.hmcts.cp.onboarding.repositories.MarketplaceRequestRepository;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(initializers = TestContainersInitialise.class)
public abstract class IntegrationTestBase {

    @Resource
    protected MockMvc mockMvc;

    @Autowired
    protected MarketplaceRequestRepository marketplaceRequestRepository;

    protected void clearAllTables() {
        log.info("Clearing all tables");
        marketplaceRequestRepository.deleteAll();
    }
}
