package io.dropwizard.jersey.params;

import io.dropwizard.jersey.errors.ErrorMessage;
import org.junit.Test;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public class IntParamTest {
    @Test
    public void anIntegerReturnsAnInteger() throws Exception {
        final IntParam param = new IntParam("200");

        assertThat(param.get())
                .isEqualTo(200);
    }

    @Test
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void aNonIntegerThrowsAnException() throws Exception {
        try {
            new IntParam("foo");
            failBecauseExceptionWasNotThrown(WebApplicationException.class);
        } catch (WebApplicationException e) {
            final Response response = e.getResponse();

            assertThat(response.getStatus())
                    .isEqualTo(400);

            ErrorMessage entity = (ErrorMessage) response.getEntity();
            assertThat(entity.getMessage())
                    .isEqualTo("\"foo\" is not a number.");
        }
    }
}
