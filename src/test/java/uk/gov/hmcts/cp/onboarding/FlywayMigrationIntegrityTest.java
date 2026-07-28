package uk.gov.hmcts.cp.onboarding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * Guards against modification of existing Flyway migration scripts.
 * If this test fails because a checksum mismatches: restore the original file and create a new migration instead.
 * If a file is unregistered: add the SHA-256 shown in the failure to EXPECTED_CHECKSUMS below.
 */
class FlywayMigrationIntegrityTest {

    private static final String MIGRATION_DIR = "/db/migration";

    private static final Map<String, String> EXPECTED_CHECKSUMS = Map.ofEntries(
            Map.entry("V1.001__onboarding_request_schema.sql", "7f31f58958abc637cd71517ade30f877417440a378655ce8a68830512b6bb50f"), // gitleaks:allow
            Map.entry("V1.002__publish_new_api_contact_schema.sql", "aac52941a48cbe5da2b8abc9c1f5e3501f93eb9fc5e974444805f15431b91dd5") // gitleaks:allow
    );

    @Test
    void all_migration_files_must_have_a_registered_checksum()
            throws URISyntaxException, IOException, NoSuchAlgorithmException {
        final List<String> migrationFiles = listMigrationFiles();
        assertThat(migrationFiles).isNotEmpty();

        final List<String> unregistered = migrationFiles.stream()
                .filter(f -> !EXPECTED_CHECKSUMS.containsKey(f))
                .toList();

        if (!unregistered.isEmpty()) {
            final StringBuilder hint = new StringBuilder(
                    "New migration(s) found without a registered checksum. "
                            + "Add the following to EXPECTED_CHECKSUMS in FlywayMigrationIntegrityTest:\n");
            for (final String filename : unregistered) {
                final String checksum = sha256(MIGRATION_DIR + "/" + filename);
                hint.append(String.format("  \"%s\", \"%s\"%n", filename, checksum));
            }
            fail(hint.toString());
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("registeredMigrations")
    void migration_script_must_not_be_modified(final String filename, final String expectedSha256)
            throws IOException, NoSuchAlgorithmException {
        final String actual = sha256(MIGRATION_DIR + "/" + filename);
        assertThat(actual)
                .as("Migration '%s' has been modified. "
                        + "Editing applied Flyway migrations breaks all environments. "
                        + "Create a new migration script instead.", filename)
                .isEqualTo(expectedSha256);
    }

    static Stream<Arguments> registeredMigrations() {
        return EXPECTED_CHECKSUMS.entrySet().stream()
                .map(e -> Arguments.of(e.getKey(), e.getValue()));
    }

    private List<String> listMigrationFiles() throws URISyntaxException {
        final URL dirUrl = getClass().getResource(MIGRATION_DIR);
        assertThat(dirUrl).as("Migration directory not found on classpath: %s", MIGRATION_DIR).isNotNull();
        final File dir = new File(dirUrl.toURI());
        final String[] files = dir.list((d, name) -> name.endsWith(".sql"));
        assertThat(files).as("No .sql files found in %s", MIGRATION_DIR).isNotNull();
        return Arrays.asList(files);
    }

    private String sha256(final String classpathResource) throws IOException, NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (InputStream in = getClass().getResourceAsStream(classpathResource)) {
            assertThat(in)
                    .as("Migration file not found on classpath: %s", classpathResource)
                    .isNotNull();
            try (DigestInputStream dis = new DigestInputStream(in, digest)) {
                dis.transferTo(OutputStream.nullOutputStream());
            }
        }
        return HexFormat.of().formatHex(digest.digest());
    }
}
