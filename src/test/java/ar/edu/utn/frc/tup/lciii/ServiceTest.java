package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.dtos.common.ReducedCountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceTest {

    @Mock
    private RestTemplate restTemplate;


    @InjectMocks
    private CountryService countryService;

    @Spy
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        String url = "https://restcountries.com/v3.1/all";

        List<Map<String, Object>> mockResponse = List.of(
                Map.of("name", Map.of("common", "Argentina"), "population", 45000000, "area", 2780400.0, "region", "Americas", "languages", Map.of("es", "Spanish"), "borders", List.of("BRA"), "cca3", "ARG", "continents", List.of("South America"))
        );

        when(restTemplate.getForObject(url, List.class)).thenReturn(mockResponse);


    }

    @Test
    void getAllCountries() {

        List<ReducedCountryDTO> result = countryService.getAllCountriesDTO();


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Argentina", result.get(0).getName());
        assertEquals("ARG", result.get(0).getCode());

    }

    @Test
    void getCountryByName() {



        List<ReducedCountryDTO> result = countryService.getCountryByName("Argentina");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Argentina", result.get(0).getName());
        result = countryService.getCountryByName("BRA");
        assertEquals(0, result.size());
    }

    @Test
    void getCountryByCode() {

        List<ReducedCountryDTO> result = countryService.getCountryByCode("ARG");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Argentina", result.get(0).getName());

        result = countryService.getCountryByCode("BRA");
        assertEquals(0, result.size());
    }


    @Test
    void getCountryByContinent() {


        List<ReducedCountryDTO> result = countryService.getCountryByContinent("South America");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Argentina", result.get(0).getName());

        result = countryService.getCountryByContinent("BRA");
        assertEquals(0, result.size());
    }

    @Test
    void getCountryByLanguaje() {


        List<ReducedCountryDTO> result = countryService.getCountryByLanguaje("Spanish");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Argentina", result.get(0).getName());

        result = countryService.getCountryByLanguaje("BRA");
        assertEquals(0, result.size());
    }

}