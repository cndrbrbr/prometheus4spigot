package de.schule.prometheus;

import io.prometheus.metrics.core.metrics.Gauge;
import org.bukkit.World;

public class MinecraftMetrics {

    private final PrometheusExporter plugin;
    private final TpsMonitor tpsMonitor;

    private Gauge playersOnline;
    private Gauge playersMax;

    private Gauge tps;

    private Gauge loadedChunks;
    private Gauge entities;

    private Gauge worlds;

    public MinecraftMetrics(
            PrometheusExporter plugin,
            TpsMonitor tpsMonitor) {

        this.plugin = plugin;
        this.tpsMonitor = tpsMonitor;
    }

    public void register() {

        if (plugin.getConfig()
                .getBoolean("metrics.players", true)) {

            playersOnline = Gauge.builder()
                    .name("minecraft_players_online")
                    .help("Currently connected players")
                    .register();

            playersMax = Gauge.builder()
                    .name("minecraft_players_max")
                    .help("Maximum configured player count")
                    .register();
        }

        if (plugin.getConfig()
                .getBoolean("metrics.tps", true)) {

            tps = Gauge.builder()
                    .name("minecraft_tps")
                    .help("Estimated server ticks per second")
                    .register();
        }

        if (plugin.getConfig()
                .getBoolean("metrics.worlds", true)) {

            worlds = Gauge.builder()
                    .name("minecraft_worlds")
                    .help("Number of loaded Minecraft worlds")
                    .register();
        }

        if (plugin.getConfig()
                .getBoolean("metrics.chunks", true)) {

            loadedChunks = Gauge.builder()
                    .name("minecraft_loaded_chunks")
                    .help("Number of loaded chunks")
                    .labelNames("world")
                    .register();
        }

        if (plugin.getConfig()
                .getBoolean("metrics.entities", true)) {

            entities = Gauge.builder()
                    .name("minecraft_entities")
                    .help("Number of loaded entities")
                    .labelNames("world")
                    .register();
        }
    }

    public void update() {

        if (playersOnline != null) {

            playersOnline.set(
                    plugin.getServer()
                            .getOnlinePlayers()
                            .size()
            );

            playersMax.set(
                    plugin.getServer()
                            .getMaxPlayers()
            );
        }

        if (tps != null) {
            tps.set(
                    tpsMonitor.getTps()
            );
        }

        if (worlds != null) {

            worlds.set(
                    plugin.getServer()
                            .getWorlds()
                            .size()
            );
        }

        for (World world :
                plugin.getServer().getWorlds()) {

            if (loadedChunks != null) {

                loadedChunks
                        .labelValues(world.getName())
                        .set(
                                world.getLoadedChunks()
                                        .length
                        );
            }

            if (entities != null) {

                entities
                        .labelValues(world.getName())
                        .set(
                                world.getEntities()
                                        .size()
                        );
            }
        }
    }
}