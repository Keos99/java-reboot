package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TravelServiceTest {

    @Test
    public void testAdd() {
        TravelService travelService = new TravelService();

        CityInfo city1 = new CityInfo("New York",
                new GeoPosition("55(45'17'')", "37(30'00'')"));
        CityInfo city2 = new CityInfo("Los Angeles",
                new GeoPosition("55(45'17'')", "37(30'00'')"));

        travelService.add(city1);
        assertEquals(1, travelService.citiesNames().size());
        assertTrue(travelService.citiesNames().contains(city1.getName()));

        travelService.add(city2);
        assertEquals(2, travelService.citiesNames().size());
        assertTrue(travelService.citiesNames().contains(city2.getName()));

        assertThrows(IllegalArgumentException.class, () -> travelService.add(city1));
    }

    @Test
    public void testRemove() {
        TravelService travelService = new TravelService();

        CityInfo city1 = new CityInfo("New York",
                new GeoPosition("55(45'17'')", "37(30'00'')"));
        CityInfo city2 = new CityInfo("Los Angeles",
                new GeoPosition("55(45'17'')", "37(30'00'')"));

        travelService.add(city1);
        travelService.add(city2);

        travelService.remove("New York");
        assertEquals(1, travelService.citiesNames().size());
        assertTrue(travelService.citiesNames().contains(city2.getName()));
        assertFalse(travelService.citiesNames().contains(city1.getName()));

        assertThrows(IllegalArgumentException.class, () -> travelService.remove("Chicago"));
    }

    @Test
    public void testCitiesNamesEmptyList() {
        TravelService travelService = new TravelService();
        List<String> cityNames = travelService.citiesNames();
        assertEquals(0, cityNames.size());
    }

    @Test
    public void testCitiesNamesNonEmptyList() {
        TravelService travelService = new TravelService();

        CityInfo city1 = new CityInfo("New York",
                new GeoPosition("55(45'17'')", "37(30'00'')"));
        CityInfo city2 = new CityInfo("Los Angeles",
                new GeoPosition("55(45'17'')", "37(30'00'')"));

        travelService.add(city1);
        travelService.add(city2);

        List<String> cityNames = travelService.citiesNames();
        assertEquals(2, cityNames.size());
        assertTrue(cityNames.contains("New York"));
        assertTrue(cityNames.contains("Los Angeles"));
    }

    @Test
    public void testGetDistanceValidCities() {
        TravelService travelService = new TravelService();

        CityInfo city1 = new CityInfo("New York",
                new GeoPosition("40", "74"));
        CityInfo city2 = new CityInfo("Los Angeles",
                new GeoPosition("33", "118"));

        travelService.add(city1);
        travelService.add(city2);

        int distance = travelService.getDistance("New York", "Los Angeles");
        assertEquals(3968, distance, 0.001);
    }

    @Test
    public void testGetDistanceInvalidCityName() {
        TravelService travelService = new TravelService();

        CityInfo city1 = new CityInfo("New York",
                new GeoPosition("40", "74"));
        travelService.add(city1);

        assertThrows(IllegalArgumentException.class, () -> travelService.getDistance("New York", "Chicago"));
    }

    @Test
    public void testGetCitiesNearValidCityAndRadius() {
        TravelService travelService = new TravelService();

        CityInfo city1 = new CityInfo("New York",
                new GeoPosition("40", "74"));
        CityInfo city2 = new CityInfo("Boston",
                new GeoPosition("42", "71"));
        CityInfo city3 = new CityInfo("Philadelphia",
                new GeoPosition("39", "75"));
        CityInfo city4 = new CityInfo("Washington D.C.",
                new GeoPosition("38", "77"));

        travelService.add(city1);
        travelService.add(city2);
        travelService.add(city3);
        travelService.add(city4);

        List<String> nearCities = travelService.getCitiesNear("New York", 300);
        assertEquals(1, nearCities.size());
        assertFalse(nearCities.contains("Boston"));
        assertTrue(nearCities.contains("Philadelphia"));
        assertFalse(nearCities.contains("Washington D.C."));
    }

    @Test
    public void testGetCitiesNearInvalidCityName() {
        TravelService travelService = new TravelService();

        CityInfo city1 = new CityInfo("New York",
                new GeoPosition("40", "74"));
        travelService.add(city1);

        assertThrows(IllegalArgumentException.class, () -> travelService.getCitiesNear("Chicago", 100));
    }
}