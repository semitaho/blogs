package com.taho.guides.useoptional;

import java.util.Optional;

public record Person(Long id, String name, Optional<Car> carOptional) {
}
