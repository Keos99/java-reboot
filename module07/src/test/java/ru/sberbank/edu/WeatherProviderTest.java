package ru.sberbank.edu;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeatherProviderTest {

    Gson gson = new Gson();
    @Test
    public void getWeatherFirsRequest() {
        String city = "Moscow";
        WeatherProvider weatherProvider = new WeatherProvider();
        WeatherInfo weatherInfo = weatherProvider.get(city);

        assertEquals(weatherInfo.getCity(), city);
    }
}