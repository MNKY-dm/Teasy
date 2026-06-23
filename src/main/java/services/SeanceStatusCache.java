package services;

import models.enums.SeanceStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class SeanceStatusCache {

    private static final SeanceStatusCache instance = new SeanceStatusCache();

    private final Map<Integer, SeanceStatus> cache = new ConcurrentHashMap<>();

    private SeanceStatusCache() {
    }

    public static SeanceStatusCache getInstance() {
        return instance;
    }

    public SeanceStatus get(int seanceId) {
        return cache.get(seanceId);
    }

    public SeanceStatus getOrDefault(int seanceId, SeanceStatus defaultStatus) {
        return cache.getOrDefault(seanceId, defaultStatus);
    }

    public SeanceStatus getOrCompute(int seanceId, Supplier<SeanceStatus> statusSupplier) {
        return cache.computeIfAbsent(seanceId, id -> statusSupplier.get());
    }

    public void put(int seanceId, SeanceStatus status) {
        if (status == null) {
            cache.remove(seanceId);
            return;
        }
        cache.put(seanceId, status);
    }

    public boolean contains(int seanceId) {
        return cache.containsKey(seanceId);
    }

    public void invalidate(int seanceId) {
        cache.remove(seanceId);
    }

    public void clear() {
        cache.clear();
    }

    public int size() {
        return cache.size();
    }
}
