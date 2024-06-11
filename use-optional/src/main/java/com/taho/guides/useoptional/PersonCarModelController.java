package com.taho.guides.useoptional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.taho.guides.useoptional.MemoryDb.CAR_MODEL_POWER_MAP;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class PersonCarModelController {

  public final PersonCarModelService personCarModelService;

  public PersonCarModelController(final PersonCarModelService personCarModelService) {
    this.personCarModelService = personCarModelService;
  }

  @GetMapping("/person/{personId}/carpower")
  public ResponseEntity<PersonWithCarPower> getPersonCarPower(@PathVariable final Long personId) {
    return personCarModelService.getPersonsCarModel(personId)
            .map(carmodel ->
                    ok(new PersonWithCarPower(personId, CAR_MODEL_POWER_MAP.get(carmodel))
                    )).orElseGet(() ->
                    ResponseEntity.notFound().build()
            );
  }
}
