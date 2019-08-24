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
package pl.tlinkowski.unij.service.collect.guava;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableList;

import pl.tlinkowski.unij.service.api.UniJService;
import pl.tlinkowski.unij.service.api.collect.UnmodifiableListFactory;

/**
 * Implementation of {@link UnmodifiableListFactory} that returns Guava's {@link ImmutableList}s.
 *
 * @author Tomasz Linkowski
 */
@UniJService(priority = 20)
@AutoService(UnmodifiableListFactory.class)
public final class GuavaUnmodifiableListFactory implements UnmodifiableListFactory {

  //region COLLECTOR
  @SuppressWarnings("unchecked")
  @Override
  public <E> Collector<E, ?, List<E>> collector() {
    return (Collector<E, ?, List<E>>) (Collector<E, ?, ?>) ImmutableList.toImmutableList();
  }
  //endregion

  //region COPY OF
  @Override
  public <E> List<E> copyOf(Collection<? extends E> coll) {
    return ImmutableList.copyOf(coll);
  }
  //endregion

  //region OF
  @Override
  public <E> List<E> of() {
    return ImmutableList.of();
  }

  @Override
  public <E> List<E> of(E e1) {
    return ImmutableList.of(e1);
  }

  @Override
  public <E> List<E> of(E e1, E e2) {
    return ImmutableList.of(e1, e2);
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3) {
    return ImmutableList.of(e1, e2, e3);
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4) {
    return ImmutableList.of(e1, e2, e3, e4);
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5) {
    return ImmutableList.of(e1, e2, e3, e4, e5);
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
    return ImmutableList.of(e1, e2, e3, e4, e5, e6);
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
    return ImmutableList.of(e1, e2, e3, e4, e5, e6, e7);
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
    return ImmutableList.of(e1, e2, e3, e4, e5, e6, e7, e8);
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
    return ImmutableList.of(e1, e2, e3, e4, e5, e6, e7, e8, e9);
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
    return ImmutableList.of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
  }

  @SafeVarargs
  @Override
  public final <E> List<E> of(E... elements) {
    return ImmutableList.copyOf(elements);
  }
  //endregion
}
