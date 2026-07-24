package uk.gov.hmcts.cp.integration.config;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresInitialiseForTest implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(final ConfigurableApplicationContext ctx) {
        assertPostgresReachable("jdbc:postgresql://localhost:5432/appdb", "postgres", "postgres");
        TestPropertyValues.of(
                "spring.datasource.url=jdbc:postgresql://localhost:5432/appdb",
                "spring.datasource.username=postgres",
                "spring.datasource.password=postgres",
                "spring.datasource.hikari.maximum-pool-size=4"
        ).applyTo(ctx.getEnvironment());
    }

    static void assertPostgresReachable(final String url, final String user, final String password) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "\n\n*** Integration tests require PostgreSQL on localhost:5432 (database: appdb) ***\n"
                    + "Start postgres:\n"
                    + "  docker compose -f docker/docker-compose.yml up -d postgres\n\n",
                    e);
        }
    }
}
