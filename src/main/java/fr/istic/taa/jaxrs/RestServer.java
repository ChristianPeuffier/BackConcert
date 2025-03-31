package fr.istic.taa.jaxrs;

import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import java.util.logging.Logger;

/**
 * RESTfull microservice, based on JAX-RS and JBoss Undertow.
 * */
public final class
RestServer {

    private RestServer() {
        throw new UnsupportedOperationException("No RestServer instances for you!");
    }

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(RestServer.class.getName());

    /**
     * Main method.
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {

        UndertowJaxrsServer ut = new UndertowJaxrsServer();

        TestApplication ta = new TestApplication();

        final int port = 8080;

        ut.deploy(ta);

        ut.start(
                Undertow.builder()
                        .addHttpListener(port, "localhost")

        );

        LOGGER.info("JAX-RS based micro-service running!");
    }
}
