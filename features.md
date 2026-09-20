# functions
minecraft_players_online  
minecraft_players_max  
minecraft_tps  
minecraft_worlds  
minecraft_chunks_loaded  
minecraft_entities  
minecraft_server_uptime_seconds  
JVM Heap / Non-Heap  
Threads  
Garbage Collection  
Prozess-/JVM-Metriken  

spigot-prometheus-exporter/  
├── pom.xml  
└── src/  
    └── main/  
        ├── java/  
        │   └── de/schule/prometheus/  
        │       ├── PrometheusExporter.java  
        │       ├── MetricsServer.java  
        │       └── MinecraftMetrics.java  
        └── resources/  
            ├── plugin.yml  
            └── config.yml  

# config.yml
metrics:  
  host: "0.0.0.0"  
  port: 9940  

minecraft:  
  collect-tps: true  
  collect-worlds: true  
  collect-entities: true  

jvm:  
  enabled: true  
