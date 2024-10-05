package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.common.ReducedCountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/countries")
public class CountryController {


    @Autowired
    private CountryService countryService;

    @GetMapping()
    public ResponseEntity<List<ReducedCountryDTO>> getAllCountries(@RequestParam(required = false) String name, @RequestParam(required = false) String code) {


      if(name !=null){
          return ResponseEntity.ok(countryService.getCountryByName(name));
      } else if (code!=null) {
          return ResponseEntity.ok(countryService.getCountryByCode(code));

      }else {
          return ResponseEntity.ok(countryService.getAllCountriesDTO());
      }


    }

    @GetMapping("/{continent}/continent")
    public ResponseEntity<List<ReducedCountryDTO>> getCountryByContinent(@PathVariable String continent) {

        return ResponseEntity.ok(countryService.getCountryByContinent(continent));


    }

    @GetMapping("/{language}/language")
    public ResponseEntity<List<ReducedCountryDTO>> getCountryByLanguaje(@PathVariable String language) {

        return ResponseEntity.ok(countryService.getCountryByLanguaje(language));


    }

    @GetMapping("/most-borders")
    public ResponseEntity<ReducedCountryDTO> getMostBorders() {

        return ResponseEntity.ok(countryService.getCountryWithmostBorders());


    }




}