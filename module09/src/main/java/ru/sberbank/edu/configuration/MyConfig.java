package ru.sberbank.edu.configuration;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.edu.WeatherCache;
import ru.sberbank.edu.WeatherProvider;

@Configuration
@ComponentScan("ru.sberbank.edu")
@PropertySource("classpath:application.properties")
public class MyConfig {
    @Value("${openweather.apikey}")
    private String apiKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WeatherProvider weatherProvider() {
        return new WeatherProvider(apiKey);
    }

    @Bean
    public WeatherCache weatherCache() {
        return new WeatherCache();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
