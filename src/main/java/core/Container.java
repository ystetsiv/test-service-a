package core;

import core.service.WeatherImpl;
import core.service.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Container {

    @Bean
    public WeatherService weatherService(){return new WeatherImpl();}

}
