package core.service;

import core.HttpClient;
import core.Weather;
import core.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class WeatherImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private HttpClient httpClient;

    @Override
    public List<Weather> load() {
        return StreamSupport.stream(weatherRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Weather get(Long id) throws Exception {

        //comment in case pact contract testing
        Weather weather = weatherRepository.findById(id).orElseThrow(()-> new RuntimeException("Incorrect id " + id));
        weather.setTemperature(httpClient.sendGet(weather.getCity()));

        //Uncomment in case pact cotract testing
//        Weather weather = new Weather();
//        weather.setCity("Dnipro");
//        weather.setId(1L);
//        weather.setTemperature("29");
        return weather;
    }

    @Override
    public Weather post(Weather client) {
        return weatherRepository.save(client);
    }
}
