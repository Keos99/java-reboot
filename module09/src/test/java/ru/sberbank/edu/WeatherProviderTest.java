package ru.sberbank.edu;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sberbank.edu.configuration.MyConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherProviderTest {

    Gson gson = new Gson();
    @Test
    public void getWeatherFirsRequest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        String city = "Moscow";
        WeatherProvider weatherProvider = context.getBean(WeatherProvider.class);
        WeatherInfo weatherInfo = weatherProvider.get(city);

        assertEquals(weatherInfo.getCity(), city);
    }
}