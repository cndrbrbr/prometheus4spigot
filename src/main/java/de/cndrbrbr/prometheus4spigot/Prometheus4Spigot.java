package de.cndrbrbr.prometheus4spigot;

import io.prometheus.metrics.instrumentation.jvm.JvmMetrics;
import org.bukkit.plugin.java.JavaPlugin;

public class Prometheus4Spigot extends JavaPlugin {

    private MetricsServer metricsServer;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        if (getConfig().getBoolean("jvm.enabled", true)) {
            JvmMetrics.builder().register();
        }

        TpsMonitor tpsMonitor = new TpsMonitor();
        getServer().getScheduler().runTaskTimer(this, tpsMonitor, 1L, 1L);

        MinecraftMetrics minecraftMetrics = new MinecraftMetrics(this, tpsMonitor);
        minecraftMetrics.register();
        getServer().getScheduler().runTaskTimer(this, minecraftMetrics::update, 20L, 20L);

        metricsServer = new MetricsServer();

        try {
            metricsServer.start(
                    getConfig().getString("server.bind", "0.0.0.0"),
                    getConfig().getInt("server.port", 9940)
            );
        } catch (Exception e) {
            getLogger().severe("Could not start metrics server: " + e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        if (metricsServer != null) {
            metricsServer.stop();
        }
    }
}
