package com.taho.guides.useoptional;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.taho.guides.useoptional.PersonCarModel.TESLA;
import static com.taho.guides.useoptional.PersonCarModel.TOYOTA;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@Component
public class PersonDb {

  private static final List<Person> PERSONS = List.of(
          // toni owns car..
          new Person(2L, "Toni", Optional.of(new Car("Toyota Yaris", TOYOTA))),
          // not everyone owns car...
          new Person(13L, "Jan", empty())

          );

  public Optional<Person> getById(final Long id) {
    return PERSONS
            .stream()
            .filter(person -> person.id().equals(id))
            .findFirst();
  }
}
