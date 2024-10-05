package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.dtos.common.ReducedCountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @BeforeEach
    public void setUp(){

        ReducedCountryDTO reducedCountryDTO = new ReducedCountryDTO();
        reducedCountryDTO.setCode("ARG");
        reducedCountryDTO.setName("Argentina");

        List<ReducedCountryDTO> reducedCountryDTOS = List.of(reducedCountryDTO);

        when(countryService.getCountryByCode(any())).thenReturn(reducedCountryDTOS);
        when(countryService.getCountryByContinent(any())).thenReturn(reducedCountryDTOS);
        when(countryService.getAllCountriesDTO()).thenReturn(reducedCountryDTOS);
        when(countryService.getCountryByName(any())).thenReturn(reducedCountryDTOS);
        when(countryService.getCountryByLanguaje(any())).thenReturn(reducedCountryDTOS);
        when(countryService.getCountryByCode(any())).thenReturn(reducedCountryDTOS);




    }

    @Test
    public void allCountries() throws Exception {



        mockMvc.perform(get("/api/countries")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(countryService, times(1)).getAllCountriesDTO();
    }
    @Test
    public void countriesByName() throws Exception {



        mockMvc.perform(get("/api/countries")
                        .param("name", "Argentina")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(countryService, times(1)).getCountryByName(any());
    }
    @Test
    public void countriesByCode() throws Exception {



        mockMvc.perform(get("/api/countries")
                        .param("code", "Ar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(countryService, times(1)).getCountryByCode(any());
    }


    @Test
    public void countriesByContinent() throws Exception {



        mockMvc.perform(get("/api/countries/{continent}/continent", "America")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(countryService, times(1)).getCountryByContinent(any());
    }

    @Test
    public void countriesByLanguaje() throws Exception {



        mockMvc.perform(get("/api/countries/{language}/language", "Spanish")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(countryService, times(1)).getCountryByLanguaje(any());
    }



}
