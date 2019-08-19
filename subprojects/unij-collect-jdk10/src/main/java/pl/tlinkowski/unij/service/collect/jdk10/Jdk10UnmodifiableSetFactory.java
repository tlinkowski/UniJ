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
package pl.tlinkowski.unij.service.collect.jdk10;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import pl.tlinkowski.unij.service.api.UniJService;
import pl.tlinkowski.unij.service.api.collect.UnmodifiableSetFactory;

/**
 * Implementation of {@link UnmodifiableSetFactory} that returns <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Set.html#unmodifiable">unmodifiable
 * sets</a> introduced in JDK 10.
 *
 * @author Tomasz Linkowski
 */
@UniJService(priority = 10)
public final class Jdk10UnmodifiableSetFactory implements UnmodifiableSetFactory {

  //region COLLECTOR
  @Override
  public <E> Collector<E, ?, Set<E>> collector() {
    return Collectors.toUnmodifiableSet();
  }
  //endregion

  //region COPY OF
  @Override
  public <E> Set<E> copyOf(Collection<? extends E> coll) {
    return Set.copyOf(coll);
  }
  //endregion

  //region OF
  @Override
  public <E> Set<E> of() {
    return Set.of();
  }

  @Override
  public <E> Set<E> of(E e1) {
    return Set.of(e1);
  }

  @Override
  public <E> Set<E> of(E e1, E e2) {
    return Set.of(e1, e2);
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3) {
    return Set.of(e1, e2, e3);
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4) {
    return Set.of(e1, e2, e3, e4);
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5) {
    return Set.of(e1, e2, e3, e4, e5);
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
    return Set.of(e1, e2, e3, e4, e5, e6);
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
    return Set.of(e1, e2, e3, e4, e5, e6, e7);
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
    return Set.of(e1, e2, e3, e4, e5, e6, e7, e8);
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
    return Set.of(e1, e2, e3, e4, e5, e6, e7, e8, e9);
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
    return Set.of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <E> Set<E> of(E... elements) {
    return Set.of(elements);
  }
  //endregion
}
