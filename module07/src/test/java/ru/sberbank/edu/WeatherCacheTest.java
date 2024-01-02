package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WeatherCacheTest {

    @Test
    public void getCityFirstRequest(){
        String city = "Moscow";
        WeatherProvider provider = new WeatherProvider();
        WeatherCache cache = new WeatherCache(provider);
        WeatherInfo info = cache.getWeatherInfo(city);

        assertEquals(info.getCity(), city);
    }

    @Test
    public void getWrongCityRequest(){
        WeatherProvider provider = new WeatherProvider();
        WeatherCache cache = new WeatherCache(provider);
        WeatherInfo info = cache.getWeatherInfo("qwerty");

        assertNull(info);
    }

    @Test
    public void getCachedInfo(){
        String city = "Moscow";
        LocalDateTime expireTime;
        WeatherProvider provider = new WeatherProvider();
        WeatherCache cache = new WeatherCache(provider);
        WeatherInfo info = cache.getWeatherInfo(city);
        expireTime = info.getExpiryTime();

        info = cache.getWeatherInfo(city);
        assertEquals(expireTime, info.getExpiryTime());
    }

    @Test
    public void getExpireCachedInfo(){
        String city = "Moscow";
        LocalDateTime expireTime;
        WeatherProvider provider = new WeatherProvider();
        WeatherCache cache = new WeatherCache(provider);
        cache.setCacheExpireTimeInMinutes(-5);
        WeatherInfo info = cache.getWeatherInfo(city);
        expireTime = info.getExpiryTime();

        info = cache.getWeatherInfo(city);
        assertNotEquals(expireTime, info.getExpiryTime());
    }

}