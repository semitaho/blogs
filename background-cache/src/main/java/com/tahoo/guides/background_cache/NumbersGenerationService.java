package com.tahoo.guides.background_cache;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.LongStream;

public class NumbersGenerationService implements Supplier<List<Long>> {

  @Override
  public List<Long> get() {
    return LongStream.range(0, 100000000)
            .boxed()
            .toList();
  }
}
