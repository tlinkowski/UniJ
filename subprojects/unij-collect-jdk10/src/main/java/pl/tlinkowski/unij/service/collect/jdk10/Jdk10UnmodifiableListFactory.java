/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2019 Tomasz Linkowski.
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

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import pl.tlinkowski.unij.service.UniJService;
import pl.tlinkowski.unij.service.collect.api.UnmodifiableListFactory;

/**
 * Implementation of {@link UnmodifiableListFactory} that returns <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html#unmodifiable">unmodifiable
 * lists</a> introduced in JDK 10.
 *
 * @author Tomasz Linkowski
 */
@UniJService(priority = 10)
public final class Jdk10UnmodifiableListFactory implements UnmodifiableListFactory {

  //region COLLECTOR
  @Override
  public <E> Collector<E, ?, List<E>> collector() {
    return Collectors.toUnmodifiableList();
  }
  //endregion

  //region COPY OF
  @Override
  public <E> List<E> copyOf(Collection<? extends E> coll) {
    return List.copyOf(coll);
  }
  //endregion

  //region OF
  @Override
  public <E> List<E> of() {
    return List.of();
  }

  @Override
  public <E> List<E> of(E e1) {
    return List.of(e1);
  }

  @Override
  public <E> List<E> of(E e1, E e2) {
    return List.of(e1, e2);
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3) {
    return List.of(e1, e2, e3);
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4) {
    return List.of(e1, e2, e3, e4);
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5) {
    return List.of(e1, e2, e3, e4, e5);
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
    return List.of(e1, e2, e3, e4, e5, e6);
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
    return List.of(e1, e2, e3, e4, e5, e6, e7);
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
    return List.of(e1, e2, e3, e4, e5, e6, e7, e8);
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
    return List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9);
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
    return List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <E> List<E> of(E... elements) {
    return List.of(elements);
  }
  //endregion
}
