package com.taho.guides.useoptional;

import java.util.Optional;

record Person(Long id, String name, String city, Optional<Car> carOptional) {
}
