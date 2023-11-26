package ru.sberbank.edu;

/**
 * City info
 */
public class CityInfo {

    /**
     * Название города
     */
    private String name;
    /**
     * Координаты расположения города
     */
    private GeoPosition position;

    /**
     * Ctor.
     *
     * @param name     - city name
     * @param position - position
     */
    public CityInfo(String name, GeoPosition position) {
        this.name = name;
        this.position = position;
    }

    /**
     * Получить название города
     * @return название города
     */
    public String getName() {
        return name;
    }

    /**
     * Получить геопозицию города
     * @return геопозиция города
     */
    public GeoPosition getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}