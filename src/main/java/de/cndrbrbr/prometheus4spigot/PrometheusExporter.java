package de.schule.prometheus;

import io.prometheus.metrics.exporter.httpserver.HTTPServer;
import io.prometheus.metrics.instrumentation.jvm.JvmMetrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.InetAddress;

public class PrometheusExporter extends JavaPlugin {

    private HTTPServer httpServer;
    private MinecraftMetrics minecraftMetrics;
    private TpsMonitor tpsMonitor;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        try {
            startExporter();
        } catch (Exception e) {
            getLogger().severe(
                    "Prometheus exporter could not be started: "
                            + e.getMessage()
            );

            getServer().getPluginManager().disablePlugin(this);
        }
    }

    private void startExporter() throws IOException {

        String bind = getConfig().getString(
                "http.bind",
                "0.0.0.0"
        );

        int port = getConfig().getInt(
                "http.port",
                9940
        );

        if (getConfig().getBoolean("metrics.jvm", true)) {
            JvmMetrics.builder().register();
        }

        tpsMonitor = new TpsMonitor();

        if (getConfig().getBoolean("metrics.tps", true)) {
            getServer()
                    .getScheduler()
                    .runTaskTimer(
                            this,
                            tpsMonitor,
                            1L,
                            1L
                    );
        }

        minecraftMetrics =
                new MinecraftMetrics(
                        this,
                        tpsMonitor
                );

        minecraftMetrics.register();

        getServer()
                .getScheduler()
                .runTaskTimer(
                        this,
                        minecraftMetrics::update,
                        20L,
                        20L
                );

        httpServer = HTTPServer.builder()
                .inetAddress(InetAddress.getByName(bind))
                .port(port)
                .buildAndStart();

        getLogger().info(
                "Prometheus exporter listening on "
                        + bind
                        + ":"
                        + port
                        + "/metrics"
        );
    }

    @Override
    public void onDisable() {

        if (httpServer != null) {
            httpServer.close();
        }

        getLogger().info(
                "Prometheus exporter stopped."
        );
    }
}