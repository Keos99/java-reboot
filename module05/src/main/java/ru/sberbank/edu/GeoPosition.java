package ru.sberbank.edu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Geo position.
 */
public class GeoPosition {

    /**
     * Широта в радианах.
     */
    private double latitude;

    /**
     * Долгота в радианах.
     */
    private double longitude;

    /**
     * Ctor.
     *
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus) {
        this.latitude = toRadians(toDouble(latitudeGradus));
        this.longitude = toRadians(toDouble(longitudeGradus));
    }

    /**
     * Ctor. Создан для варианта с регулярным выражением
     *
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus, boolean isRegExp) {
        this.latitude = toRadians(toDoubleRegular(latitudeGradus));
        this.longitude = toRadians(toDoubleRegular(longitudeGradus));
    }

    /**
     * Получение значения широты в радианах
     *
     * @return значение широты в радианах
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Получение значения долготы в радианах
     *
     * @return значение долготы в радианах
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Преобразование градусов в радианы
     *
     * @param degrees значение в градусах
     * @return значение в радианах
     */
    private double toRadians(double degrees) {
        return degrees * Math.PI / 180.0;
    }

    /**
     * Преобразование String значения координат в градусах в Double
     *
     * @param value строковое значение координат в градусах
     * @return Double значеник координат в градусах
     * @throws NullPointerException если строка пуста, содержит только пробелы или содержит null
     */
    private double toDouble(String value) {
        if (value == null || value.isBlank() || value.isEmpty()) throw new NullPointerException();

        int minutes = 0;
        int seconds = 0;

        if (value.length() < 4) return Integer.parseInt(value);

        int degrees = Integer.parseInt(value.substring(0, value.indexOf("(")));
        if (value.contains("''")) {
            seconds = Integer.parseInt(value.substring(value.indexOf("''") - 2, value.indexOf("''")));
        }
        if (value.contains("'") && value.charAt(value.indexOf("'") + 1) != '\'') {
            minutes = Integer.parseInt(value.substring(value.indexOf("(") + 1, value.indexOf("'")));
        }

        return degrees + minutes / 60.0 + seconds / 3600.0;
    }

    /**
     * Преобразование String значения координат в градусах в Double при помощи регулярных выражений
     *
     * @param value строковое значение координат в градусах
     * @return Double значеник координат в градусах
     * @throws IllegalArgumentException если формат сообшения не совпадает с патерном
     * @throws NullPointerException     если строка пуста, содержит только пробелы или содержит null
     */
    private static double toDoubleRegular(String value) {
        if (value == null || value.isBlank() || value.isEmpty()) throw new NullPointerException();
        if (value.length() < 4) return Integer.parseInt(value);
        Pattern pattern = Pattern.compile(
                "(\\d+)\\((\\d{2})'(\\d{2})''\\)");
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Некорректное значение координаты: " + value);
        }

        int degrees = Integer.parseInt(matcher.group(1));
        int minutes = Integer.parseInt(matcher.group(2));
        int seconds = Integer.parseInt(matcher.group(3));

        return degrees + minutes / 60.0 + seconds / 3600.0;
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}