package io.dropwizard.client;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.util.Duration;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;

public class HttpClientConfigurationTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void deserializesFromJSON_Defaults() throws Exception {
        final HttpClientConfiguration config = new HttpClientConfiguration();

        // read json file with same config
        final HttpClientConfiguration readConfig = MAPPER.readValue(
                fixture("fixtures/httpClientConfigurationDefaults.json"), HttpClientConfiguration.class);

        assertThat(readConfig).isEqualTo(config);
    }

    @Test
    public void serializesToJSON_Defaults() throws Exception {
        final HttpClientConfiguration config = new HttpClientConfiguration();

        // compare ignoring whitespace
        assertThat(StringUtils.deleteWhitespace(MAPPER.writeValueAsString(config))).isEqualTo(
                StringUtils.deleteWhitespace(fixture("fixtures/httpClientConfigurationDefaults.json")));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final HttpClientConfiguration config = new HttpClientConfiguration();
        config.setConnectionTimeout(Duration.minutes(50));
        config.setCookiesEnabled(false);
        config.setKeepAlive(Duration.minutes(20));
        config.setMaxConnections(5);
        config.setMaxConnectionsPerRoute(23);
        config.setRetries(2);
        config.setTimeout(Duration.hours(1));
        config.setTimeToLive(Duration.seconds(23l));
        config.setUserAgent(Optional.of("htmlunit"));

        // read json file with same config
        final HttpClientConfiguration readConfig = MAPPER.readValue(fixture("fixtures/httpClientConfiguration.json"),
                HttpClientConfiguration.class);

        assertThat(readConfig).isEqualTo(config);
    }

    @Test
    public void serializesToJSON() throws Exception {
        final HttpClientConfiguration config = new HttpClientConfiguration();
        config.setConnectionTimeout(Duration.minutes(50));
        config.setCookiesEnabled(false);
        config.setKeepAlive(Duration.minutes(20));
        config.setMaxConnections(5);
        config.setMaxConnectionsPerRoute(23);
        config.setRetries(2);
        config.setTimeout(Duration.hours(1));
        config.setTimeToLive(Duration.seconds(23l));
        config.setUserAgent(Optional.of("htmlunit"));
        config.setProxyUri(Optional.of("http://localhost:8080"));

        // compare ignoring whitespace
        assertThat(StringUtils.deleteWhitespace(MAPPER.writeValueAsString(config))).isEqualTo(
                StringUtils.deleteWhitespace(fixture("fixtures/httpClientConfiguration.json")));
    }
}