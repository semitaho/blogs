package com.taho.guides.useoptional;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonCarModelService {

  private final MemoryDb memoryDb;

  public PersonCarModelService(final MemoryDb memoryDb) {
    this.memoryDb = memoryDb;
  }

  public Optional<CarModel> getPersonsCarModel(final Long personId) {
    return memoryDb.getById(personId)
            .flatMap(Person::carOptional)
            .map(Car::model);
  }
}
