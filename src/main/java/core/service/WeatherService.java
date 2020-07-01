package core.service;

import core.Weather;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeatherService {

    List<Weather> load();
    Weather get(Long id);
    Weather post(Weather client);

}
