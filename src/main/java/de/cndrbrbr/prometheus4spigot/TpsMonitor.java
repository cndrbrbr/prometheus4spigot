package de.cndrbrbr.prometheus4spigot;

public class TpsMonitor implements Runnable {

    private long lastTick = System.nanoTime();

    private double tps = 20.0;

    private static final double ALPHA = 0.05;

    @Override
    public void run() {

        long now = System.nanoTime();

        long elapsed = now - lastTick;

        lastTick = now;

        if (elapsed <= 0) {
            return;
        }

        double seconds =
                elapsed / 1_000_000_000.0;

        double currentTps =
                Math.min(
                        20.0,
                        1.0 / seconds
                );

        tps =
                (ALPHA * currentTps)
                        + ((1.0 - ALPHA) * tps);
    }

    public double getTps() {
        return tps;
    }
}