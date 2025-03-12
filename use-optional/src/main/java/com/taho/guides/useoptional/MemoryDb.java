package com.taho.guides.useoptional;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.taho.guides.useoptional.CarModel.NISSAN;
import static com.taho.guides.useoptional.CarModel.TESLA;
import static com.taho.guides.useoptional.CarModel.TOYOTA;
import static com.taho.guides.useoptional.CarPower.ELECTRIC;
import static com.taho.guides.useoptional.CarPower.HYBRID;
import static com.taho.guides.useoptional.CarPower.PETROL;
import static java.util.Optional.empty;

@Component
public class MemoryDb {


  static final Map<CarModel, CarPower> CAR_MODEL_POWER_MAP =
          Map.of(
                  NISSAN, PETROL,
                  TOYOTA, HYBRID,
                  TESLA, ELECTRIC);
  static final List<Person> PERSONS = List.of(
          // toni owns car..
          new Person(2L, "Toni", "Vimpeli", Optional.of(new Car("Toyota Yaris", TOYOTA))),
          // not everyone owns car...
          new Person(13L, "Jan", "Espoo",empty())

  );


  public Optional<Person> getById(final Long id) {
    return PERSONS
            .stream()
            .filter(person -> person.id().equals(id))
            .findFirst();
  }
}
