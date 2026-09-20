# non functions
- Der Minecraft-Server muss nicht von überall auf Port 9940 erreichbar sein. Idealerweise darf nur der zentrale Prometheus-Server darauf zugreifen.  
- Die aktuelle Prometheus-Java-Library bringt schon einen fertigen HTTP-Server mit /metrics mit
- keine Spielernamen
- Servernamen wahrscheinlich  zentral in Prometheus konfigurieren
- Der Minecraft-Server muss nicht von überall auf Port 9940 erreichbar sein, es reicht wenn der zentrale grafana oder prometheus server zugreifen kann  
- 

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

# später
Spieler-Logins, Deaths, Join/Leave-Counter, Plugin-Zahl, Weltgrößen, Tick-Zeiten  

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
