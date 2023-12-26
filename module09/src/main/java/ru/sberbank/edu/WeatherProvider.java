package ru.sberbank.edu;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.edu.model.WeatherData;

@Component
public class WeatherProvider {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Gson gson;
    private String apiKey;

    public WeatherProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    public WeatherInfo get(String city) {
        WeatherInfo weatherInfo = null;
        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(getUrl(city, apiKey), String.class);
        } catch (HttpClientErrorException e) {
            return null;
        }
        weatherInfo = mapWeaterInfo(gson.fromJson(response.getBody(), WeatherData.class));
        return weatherInfo;
    }

    /**
     * Maps the provided WeatherData object to a WeatherInfo object.
     *
     * @param data The WeatherData object to map.
     * @return The mapped WeatherInfo object.
     */
    private WeatherInfo mapWeaterInfo(WeatherData data) {
        WeatherInfo info = new WeatherInfo();
        info.setCity(data.name);
        info.setDescription(data.getWeather().get(0).getDescription());
        info.setPressure(data.getMain().getPressure());
        info.setTemperature(data.getMain().getTemp());
        info.setFeelsLikeTemperature(data.getMain().getFeels_like());
        info.setShortDescription(data.getWeather().get(0).getDescription());
        info.setWindSpeed(data.getWind().getSpeed());
        return info;
    }

    /**
     * Generates the URL for the OpenWeatherMap API to retrieve weather data for the specified city.
     *
     * @param city   The name of the city for which to retrieve weather data.
     * @param APIKey The API key for the OpenWeatherMap API.
     * @return The URL for the OpenWeatherMap API.
     */
    private String getUrl(String city, String APIKey) {
        return "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + APIKey;
    }
}
