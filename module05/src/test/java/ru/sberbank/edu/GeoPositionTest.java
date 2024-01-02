package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoPositionTest {

    @Test
    public void testToRadians() {
        GeoPosition position = new GeoPosition("55(45'17'')", "37(30'00'')");
        double latitude = position.getLatitude();
        double longitude = position.getLongitude();

        assertEquals(0.9731034763126273, latitude, 0.000001);
        assertEquals(0.6544984694978736, longitude, 0.000001);
    }

    @Test
    public void testToRadiansWithOnlyGradus() {
        GeoPosition position = new GeoPosition("55", "37");
        double latitude = position.getLatitude();
        double longitude = position.getLongitude();

        assertEquals(0.9599310885968813, latitude, 0.000001);
        assertEquals(0.6457718232379019, longitude, 0.000001);
    }

    @Test
    public void testToRadiansWithNegativeDegrees() {
        GeoPosition position = new GeoPosition("-55(45'07'')", "-37(30'00'')");
        double latitude = position.getLatitude();
        double longitude = position.getLongitude();

        assertEquals(-0.946807182249246, latitude, 0.000001);
        assertEquals(-0.6370451769779303, longitude, 0.000001);
    }

    @Test
    public void testToRadiansRegExp() {
        GeoPosition position = new GeoPosition("55(45'17'')", "37(30'00'')", true);
        double latitude = position.getLatitude();
        double longitude = position.getLongitude();

        assertEquals(0.9731034763126273, latitude, 0.000001);
        assertEquals(0.6544984694978736, longitude, 0.000001);
    }

    @Test
    public void testToRadiansWithOnlyGradusRegExp() {
        GeoPosition position = new GeoPosition("55", "37", true);
        double latitude = position.getLatitude();
        double longitude = position.getLongitude();

        assertEquals(0.9599310885968813, latitude, 0.000001);
        assertEquals(0.6457718232379019, longitude, 0.000001);
    }
}