package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Travel Service.
 */
public class TravelService {

    // do not change type
    private List<CityInfo> cities = new ArrayList<>();

    private final double EARTH_RADIUS = 6371.01;

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        if (cities.stream().anyMatch(e -> e.getName().equals(cityInfo.getName()))) {
            throw new IllegalArgumentException("City already exists: " + cityInfo.getName());
        }
        cities.add(cityInfo);
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {
        if (cities.stream().noneMatch(e -> e.getName().equals(cityName))) {
            throw new IllegalArgumentException("City not found: " + cityName);
        }

        cities = cities.stream().filter(e -> !e.getName().equals(cityName)).collect(Collectors.toList());
    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        return cities.stream().map(CityInfo::getName).collect(Collectors.toList());
    }

    /**
     * Get distance in kilometers between two cities.
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */
    public int getDistance(String srcCityName, String destCityName) {
        CityInfo srcCity = cities.stream().filter(c -> c.getName().equals(srcCityName)).findAny().orElse(null);
        CityInfo destCity = cities.stream().filter(c -> c.getName().equals(destCityName)).findAny().orElse(null);

        if (srcCity == null || destCity == null) {
            throw new IllegalArgumentException("Invalid city name: " + srcCityName + ", " + destCityName);
        }

        return cities.stream()
                .filter(c -> c.getName().equals(srcCityName))
                .map(c -> calculateDistance(c.getPosition().getLatitude(), c.getPosition().getLongitude(),
                        destCity.getPosition().getLatitude(), destCity.getPosition().getLongitude()))
                .findAny()
                .orElse(-1);
    }

    /**
     * Рассчет дистанции между городами
     *
     * @param srcLatitude   Широта исходной точки
     * @param srcLongitude  Долгота исходной точки
     * @param destLatitude  Широта целевой точки
     * @param destLongitude Долгота целевой точки
     * @return Расстояние между точками
     */
    private int calculateDistance(double srcLatitude, double srcLongitude, double destLatitude, double destLongitude) {
        double deltaLat = srcLatitude - destLatitude;
        double deltaLon = srcLongitude - destLongitude;

        double a = Math.pow(Math.sin(deltaLat / 2.0), 2)
                + Math.cos(srcLatitude) * Math.cos(destLatitude)
                * Math.pow(Math.sin(deltaLon / 2.0), 2);

        return (int) (2 * EARTH_RADIUS * Math.asin(Math.sqrt(a)));
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) {
        CityInfo targetCity = cities.stream().filter(c -> c.getName().equals(cityName)).findAny().orElse(null);

        if (targetCity == null) {
            throw new IllegalArgumentException("City not found: " + cityName);
        }

        return cities.stream().filter(c -> !c.getName().equals(targetCity.getName()) &&
                        getDistance(targetCity.getName(), c.getName()) <= radius)
                .map(CityInfo::getName)
                .collect(Collectors.toList());
    }
}
