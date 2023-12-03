package ru.sberbank.edu;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class WeatherCache {

    private final Map<String, WeatherInfo> cache = new HashMap<>();
    private final WeatherProvider weatherProvider;
    private int cacheExpireTimeInMinutes = 5;

    /**
     * Constructor.
     *
     * @param weatherProvider - weather provider
     */
    public WeatherCache(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    public void setCacheExpireTimeInMinutes(int timeInMinutes){
        cacheExpireTimeInMinutes = timeInMinutes;
    }

    /**
     * Get ACTUAL weather info for current city or null if current city not found.
     * If cache doesn't contain weather info OR contains NOT ACTUAL info then we should download info
     * If you download weather info then you should set expiry time now() plus 5 minutes.
     * If you can't download weather info then remove weather info for current city from cache.
     *
     * @param city - city
     * @return actual weather info
     */
    public synchronized WeatherInfo getWeatherInfo(String city) {
        if (cache.containsKey(city)) {
            if (isExpired(cache.get(city).getExpiryTime())) {
                removeWeatherInfo(city);
                return updateСacheAndGet(city);
            }
            return cache.get(city);
        } else {
            return updateСacheAndGet(city);
        }
    }

    /**
     * Updates the weather info cache for the specified city and returns the updated weather info.
     *
     * @param city The city for which to update the weather info cache.
     * @return The updated weather info.
     */
    private WeatherInfo updateСacheAndGet(String city) {
        cache.put(city, weatherProvider.get(city));
        if (cache.get(city) != null && cache.containsKey(city)) {
            cache.get(city).setExpiryTime(LocalDateTime.now()
                    .plusMinutes(cacheExpireTimeInMinutes));
        } else {
            return null;
        }
        return cache.get(city);
    }

    /**
     * Checks whether the provided expiry time has passed.
     *
     * @param expiryTime The expiry time to check.
     * @return `true` if the current time is after the expiry time, `false` otherwise.
     */
    private boolean isExpired(LocalDateTime expiryTime) {
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        return currentLocalDateTime.isAfter(expiryTime);
    }

    /**
     * Remove weather info from cache.
     **/
    public synchronized void removeWeatherInfo(String city) {
        cache.remove(city);
    }
}
