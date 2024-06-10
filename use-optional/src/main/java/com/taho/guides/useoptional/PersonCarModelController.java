package com.taho.guides.useoptional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class PersonCarModelController {

  public final PersonCarModelService personCarModelService;

  public PersonCarModelController(final PersonCarModelService personCarModelService) {
    this.personCarModelService = personCarModelService;
  }

  @GetMapping("/person/{personId}/model")
  public ResponseEntity<String> getCarModel(@PathVariable final Long personId) {
    return personCarModelService.getPersonsCarModel(personId)
            .map(carmodel ->
                    new ResponseEntity<>(carmodel.name(), HttpStatus.OK)

            ).orElseGet(() ->
                    new ResponseEntity<>("Car model not found with person id: %s".formatted(personId), NOT_FOUND)
            );
  }
}
