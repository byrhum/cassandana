package io.cassandana.metrics;

import com.sun.net.httpserver.HttpServer;
import io.micrometer.prometheus.PrometheusMeterRegistry;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class HttpEndpointResponder {

    PrometheusMeterRegistry prometheusRegistry;
    int prometheusScrapePort = 9044;
    String prometheusScrapePath = "/metrics";

    public HttpEndpointResponder(PrometheusMeterRegistry prometheusRegistry){
        this.prometheusRegistry = prometheusRegistry;
    }
    public void enable() {
        try {
            System.out.println("Starting HttpServer");
            HttpServer server = HttpServer.create(new InetSocketAddress(prometheusScrapePort), 0);
            server.createContext(prometheusScrapePath, httpExchange -> {
                String response = prometheusRegistry.scrape();
                httpExchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = httpExchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            });
            new Thread(server::start).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
