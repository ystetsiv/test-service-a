package core;

import core.Weather;
import core.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping("/client")
    public Weather createClient(@Valid @RequestBody Weather client) {
        return weatherService.post(client);
    }

    @GetMapping("/client/{clientId}")
    public Weather getClient(@PathVariable Long clientId) throws Exception {
        return weatherService.get(clientId);
    }
}
