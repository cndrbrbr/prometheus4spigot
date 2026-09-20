# architecture  
  
Minecraft-Server 1  
Spigot + PrometheusExporter  
:9940/metrics  
        \  
         \  
Minecraft-Server 2 -----> ZENTRALER PROMETHEUS -----> GRAFANA  
:9940/metrics              z.B. :9090                z.B. :3000  
         /  
        /  
Minecraft-Server 3  
:9940/metrics  
  
  
# PrometheusExporter.jar  
  
HTTP Server  
 ├── GET /metrics  
 └── GET /-/healthy  
  
Metriken  
 ├── minecraft_players_online  
 ├── minecraft_players_max  
 ├── minecraft_tps  
 ├── minecraft_uptime_seconds  
 ├── minecraft_loaded_chunks  
 ├── minecraft_entities  
 ├── minecraft_worlds  
 ├── minecraft_plugins  
 │  
 └── JVM  
     ├── Heap  
     ├── GC  
     ├── Threads  
     └── CPU  
