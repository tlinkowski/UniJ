/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018-2019 Tomasz Linkowski.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.tlinkowski.unij.service.misc.jdk8;

import java.util.function.*;
import java.util.stream.*;

import com.google.auto.service.AutoService;

import pl.tlinkowski.unij.service.api.UniJService;
import pl.tlinkowski.unij.service.api.misc.MiscellaneousApiProvider;

/**
 * Implementation of {@link MiscellaneousApiProvider} that delegates to original JDK 11 APIs.
 *
 * @author Tomasz Linkowski
 */
@UniJService(priority = 40)
@AutoService(MiscellaneousApiProvider.class)
public final class Jdk8MiscellaneousApiProvider implements MiscellaneousApiProvider {

  //region COLLECTORS

  /**
   * Based on {@link Collectors#flatMapping(Function, Collector)}.
   */
  @Override
  public <T, U, A, R> Collector<T, ?, R> flatMappingCollector(Function<? super T, ? extends Stream<? extends U>> mapper,
          Collector<? super U, A, R> downstream) {
    BiConsumer<A, ? super U> accumulator = downstream.accumulator();
    BiConsumer<A, T> newAccumulator = (a, t) -> {
      try (Stream<? extends U> mappedStream = mapper.apply(t)) {
        if (mappedStream != null) {
          mappedStream.sequential().forEach(u -> accumulator.accept(a, u));
        }
      }
    };
    return collectorWithNewAccumulator(downstream, newAccumulator);
  }

  /**
   * Based on {@link Collectors#filtering(Predicate, Collector)}.
   */
  @Override
  public <T, A, R> Collector<T, ?, R> filteringCollector(Predicate<? super T> predicate,
          Collector<? super T, A, R> downstream) {
    BiConsumer<A, ? super T> accumulator = downstream.accumulator();
    BiConsumer<A, T> newAccumulator = (a, t) -> {
      if (predicate.test(t)) {
        accumulator.accept(a, t);
      }
    };
    return collectorWithNewAccumulator(downstream, newAccumulator);
  }

  private <T, A, R> Collector<T, ?, R> collectorWithNewAccumulator(Collector<?, A, R> collector,
          BiConsumer<A, T> newAccumulator) {
    return Collector.of(
            collector.supplier(), newAccumulator, collector.combiner(), collector.finisher(),
            collector.characteristics().toArray(new Collector.Characteristics[0])
    );
  }
  //endregion
}
