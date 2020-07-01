package core.service;

import core.Weather;
import core.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class WeatherImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public List<Weather> load() {
        return StreamSupport.stream(weatherRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Weather get(Long id) {
        return weatherRepository.findById(id).orElseThrow(()-> new RuntimeException("Incorrect id " + id));
    }

    @Override
    public Weather post(Weather client) {
        return weatherRepository.save(client);
    }
}
