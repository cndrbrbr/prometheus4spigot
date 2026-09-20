package de.cndrbrbr.prometheus4spigot;
private MetricsServer metricsServer;

@Override
public void onEnable() {
    saveDefaultConfig();

    metricsServer = new MetricsServer();

    try {
        metricsServer.start(
                getConfig().getString("http.bind", "0.0.0.0"),
                getConfig().getInt("http.port", 9940)
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