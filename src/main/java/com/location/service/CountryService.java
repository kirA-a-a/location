package com.location.service;

import com.location.domain.CountryJson;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CountryService {

  List<CountryJson> findAll();

  Optional<CountryJson> getCountryById(UUID id);

  CountryJson addCountry(CountryJson countryJson);

  CountryJson updateCountry(CountryJson countryJson);

}
