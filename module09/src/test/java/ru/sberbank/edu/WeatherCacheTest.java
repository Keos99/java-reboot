package ru.sberbank.edu;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sberbank.edu.configuration.MyConfig;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WeatherCacheTest {

    @Test
    public void getCityFirstRequest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        String city = "Moscow";
        WeatherCache cache = context.getBean(WeatherCache.class);
        WeatherInfo info = cache.getWeatherInfo(city);

        assertEquals(info.getCity(), city);
    }

    @Test
    public void getWrongCityRequest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        WeatherCache cache = context.getBean(WeatherCache.class);
        WeatherInfo info = cache.getWeatherInfo("qwerty");

        assertNull(info);
    }

    @Test
    public void getCachedInfo(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        String city = "Moscow";
        LocalDateTime expireTime;
        WeatherCache cache = context.getBean(WeatherCache.class);
        WeatherInfo info = cache.getWeatherInfo(city);
        expireTime = info.getExpiryTime();

        info = cache.getWeatherInfo(city);
        assertEquals(expireTime, info.getExpiryTime());
    }

    @Test
    public void getExpireCachedInfo(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        String city = "Moscow";
        LocalDateTime expireTime;
        WeatherCache cache = context.getBean(WeatherCache.class);
        cache.setCacheExpireTimeInMinutes(-5);
        WeatherInfo info = cache.getWeatherInfo(city);
        expireTime = info.getExpiryTime();

        info = cache.getWeatherInfo(city);
        assertNotEquals(expireTime, info.getExpiryTime());
    }

}