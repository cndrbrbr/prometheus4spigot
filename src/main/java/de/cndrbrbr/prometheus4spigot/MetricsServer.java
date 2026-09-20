package de.cndrbrbr.prometheus4spigot;

import io.prometheus.metrics.exporter.httpserver.HTTPServer;

import java.io.IOException;
import java.net.InetAddress;

public class src/main/resources/plugin.yml {

    private HTTPServer server;

    public void start(String bind, int port) throws IOException {
        server = HTTPServer.builder()
                .inetAddress(InetAddress.getByName(bind))
                .port(port)
                .buildAndStart();
    }

    public void stop() {
        if (server != null) {
            server.close();
        }
    }
}