package com.location.service;

import com.location.data.LocationEntity;
import com.location.data.LocationRepository;
import com.location.domain.CountryJson;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CountryDbService implements CountryService {

  LocationRepository locationRepository;

  @Autowired
  public CountryDbService(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  @Override
  public List<CountryJson> findAll() {
    return locationRepository
        .findAll()
        .stream()
        .map(ent -> new CountryJson(
            ent.getId(),
            ent.getName()
        )).toList();
  }

  @Override
  public Optional<CountryJson> getCountryById(UUID id) {
    return locationRepository
        .findById(id)
        .map(ent -> new CountryJson(
            ent.getId(),
            ent.getName()
        ));
  }

  @Override
  public CountryJson addCountry(CountryJson countryJson) {

    LocationEntity country = new LocationEntity();
    country.setName(countryJson.name());

    LocationEntity inserted = locationRepository.save(country);

    return new CountryJson(inserted.getId(), inserted.getName());
  }

  @Override
  public CountryJson updateCountry(CountryJson countryJson) {

    LocationEntity entity = locationRepository
        .findById(countryJson.id())
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found")
        );
    if (countryJson.name() != null) {
      entity.setName(countryJson.name());
    }

    LocationEntity updated = locationRepository.save(entity);
    return new CountryJson(updated.getId(), updated.getName());

  }
}
