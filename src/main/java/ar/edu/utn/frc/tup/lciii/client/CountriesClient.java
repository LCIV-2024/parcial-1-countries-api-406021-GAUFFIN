package ar.edu.utn.frc.tup.lciii.client;

import ar.edu.utn.frc.tup.lciii.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CountriesClient {
    private static final String RESILIENCE4J_INSTANCE_NAME = "postCircuitBraker";
    private static final String FALLBACK_METHOD = "fallback";


    private static List<Country> COUNTRIES_CACHE = new ArrayList<>() {
    };

    @Autowired
    private RestTemplate restTemplate;

//    String baseUrl = "https://my-json-server.typicode.com/LCIV-2023/fake-api-rwc2023/teams";
//


//    //@CircuitBreaker(name = RESILIENCE4J_INSTANCE_NAME, fallbackMethod = FALLBACK_METHOD)
//    public ResponseEntity<List<Country>> getAllCountries(){
//
//            List<Country> countries = List.of(Objects.requireNonNull(restTemplate.getForEntity(baseUrl, Country[].class).getBody()));
//        return ResponseEntity.ok(countries);

//    }
//    public ResponseEntity<Match> fallback(Exception ex){
//        return ResponseEntity.status(503).build();
//    }

}