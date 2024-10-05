package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.ReducedCountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CountryService {

//        private final CountryRepository countryRepository;

        private final RestTemplate restTemplate;

        private final ModelMapper modelMapper;

        public List<ReducedCountryDTO> getAllCountriesDTO() {
                List<ReducedCountryDTO> countryDTOS = new ArrayList<>();

                List<Country> countries = getAllCountries();



                for(Country country : countries){

                    countryDTOS.add(modelMapper.map(country, ReducedCountryDTO.class));

                }

                return countryDTOS ;
        }

        private List<Country> getAllCountries(){
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);



                List<Country> countries =  response.stream().map(this::mapToCountry).collect(Collectors.toList());

                return countries;
        }

        public List<ReducedCountryDTO> getCountryByName(String name) {

                List<ReducedCountryDTO> countryDTOS = getAllCountriesDTO();
                Stream<ReducedCountryDTO> countryStream = countryDTOS.stream().filter(country -> country.getName().equalsIgnoreCase(name));

                List<ReducedCountryDTO> filteredCountriesDTOS = new ArrayList<>(countryStream.collect(Collectors.toList()));

                return filteredCountriesDTOS;


        }

        private Country mapToCountry(Map<String, Object> countryData) {
                Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");
                return Country.builder()
                        .name((String) nameData.get("common"))
                        .population(((Number) countryData.get("population")).longValue())
                        .area(((Number) countryData.get("area")).doubleValue())
                        .region((String) countryData.get("region"))
                        .languages((Map<String, String>) countryData.get("languages"))
                        .borders((List<String>) countryData.get("borders"))
                        .code((String) countryData.get("cca3"))
                        .continent((List<String>) countryData.get("continents"))
                        .build();
        }


        public List<ReducedCountryDTO> getCountryByCode(String code) {

                List<ReducedCountryDTO> countryDTOS = getAllCountriesDTO();

                Stream<ReducedCountryDTO> countryStream = countryDTOS.stream().filter(country -> country.getCode().equalsIgnoreCase(code));

                List<ReducedCountryDTO> filteredCountriesDTOS = new ArrayList<>(countryStream.collect(Collectors.toList()));


                return filteredCountriesDTOS;


        }

        public List<ReducedCountryDTO> getCountryByContinent(String continent) {

                List<Country> countries = getAllCountries();

                Stream<Country> countryStream = countries.stream().filter(country -> country.getContinent().contains(continent));

                List<Country> filteredCountries= new ArrayList<>(countryStream.collect(Collectors.toList()));

                List<ReducedCountryDTO> reducedCountryDTOS = List.of(modelMapper.map(filteredCountries, ReducedCountryDTO[].class));


                return reducedCountryDTOS;


        }



        public List<ReducedCountryDTO> getCountryByLanguaje(String languaje) {

                List<ReducedCountryDTO> countryDTOS = new ArrayList<>();
                List<Country> countries = getAllCountries();

                for(Country country: countries){

                        if(country.getLanguages()!=null && country.getLanguages().containsValue(languaje)){
                                countryDTOS.add(modelMapper.map(country, ReducedCountryDTO.class));
                        }
                }

                return countryDTOS;


        }


}