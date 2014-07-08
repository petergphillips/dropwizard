package io.dropwizard.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.validation.ValidationMethod;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

// TODO: 5/15/13 <coda> -- write tests for JerseyClientConfiguration

/**
 * The configuration class used by {@link JerseyClientBuilder}. Extends
 * {@link HttpClientConfiguration}.
 *
 * @see HttpClientConfiguration
 * @see <a href="http://www.dropwizard.io/manual/client/#man-client-jersey-config">Jersey Client Configuration</a>
 */
public class JerseyClientConfiguration extends HttpClientConfiguration {
    @Min(1)
    @Max(16 * 1024)
    private int minThreads = 1;

    @Min(1)
    @Max(16 * 1024)
    private int maxThreads = 128;

    private boolean gzipEnabled = true;

    private boolean gzipEnabledForRequests = true;

    @JsonProperty
    public int getMinThreads() {
        return minThreads;
    }

    @JsonProperty
    public void setMinThreads(int minThreads) {
        this.minThreads = minThreads;
    }

    @JsonProperty
    public int getMaxThreads() {
        return maxThreads;
    }

    @JsonProperty
    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    @JsonProperty
    public boolean isGzipEnabled() {
        return gzipEnabled;
    }

    @JsonProperty
    public void setGzipEnabled(boolean enabled) {
        this.gzipEnabled = enabled;
    }

    @JsonProperty
    public boolean isGzipEnabledForRequests() {
        return gzipEnabledForRequests;
    }

    @JsonProperty
    public void setGzipEnabledForRequests(boolean enabled) {
        this.gzipEnabledForRequests = enabled;
    }

    @JsonIgnore
    @ValidationMethod(message = ".minThreads must be less than or equal to maxThreads")
    public boolean isThreadPoolSizedCorrectly() {
        return minThreads <= maxThreads;
    }

    @JsonIgnore
    @ValidationMethod(message = ".gzipEnabledForRequests requires gzipEnabled set to true")
    public boolean isCompressionConfigurationValid() {
        return !gzipEnabledForRequests || gzipEnabled;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (gzipEnabled ? 1231 : 1237);
        result = prime * result + (gzipEnabledForRequests ? 1231 : 1237);
        result = prime * result + maxThreads;
        result = prime * result + minThreads;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        JerseyClientConfiguration other = (JerseyClientConfiguration) obj;
        if (gzipEnabled != other.gzipEnabled)
            return false;
        if (gzipEnabledForRequests != other.gzipEnabledForRequests)
            return false;
        if (maxThreads != other.maxThreads)
            return false;
        if (minThreads != other.minThreads)
            return false;
        return true;
    }
}
