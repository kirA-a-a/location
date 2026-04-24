package com.location.controller;

import com.location.domain.CountryJson;
import com.location.service.CountryService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/country")
public class CountryController {

  private final CountryService countryService;

  @Autowired
  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @GetMapping("/all")
  public List<CountryJson> findAll() {
    return countryService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<CountryJson> getCountryById(@PathVariable UUID id) {
    return countryService.getCountryById(id);
  }

  @PostMapping
  public CountryJson addCountry(@RequestBody CountryJson countryJson) {
    return countryService.addCountry(countryJson);
  }

  @PatchMapping
  public CountryJson updateCountry(@RequestBody CountryJson countryJson) {
    return countryService.updateCountry(countryJson);
  }

}
