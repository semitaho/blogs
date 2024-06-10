package com.taho.guides.useoptional;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class PersonCarModelService {

  private final PersonDb personDb;

  public PersonCarModelService(final PersonDb personDb) {
    this.personDb = personDb;
  }

  public Optional<PersonCarModel> getPersonsCarModel(final Long personId) {
    return personDb.getById(personId)
            .flatMap(Person::carOptional)
            .map(Car::model);
  }
}
