/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018-2021 Tomasz Linkowski.
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
import java.util.Set;
import java.util.stream.Collector;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;

import pl.tlinkowski.unij.service.api.UniJService;
import pl.tlinkowski.unij.service.api.collect.UnmodifiableSetFactory;

/**
 * Implementation of {@link UnmodifiableSetFactory} that returns Guava's {@link ImmutableSet}.
 *
 * @author Tomasz Linkowski
 */
@UniJService(priority = 20)
@AutoService(UnmodifiableSetFactory.class)
public final class GuavaUnmodifiableSetFactory implements UnmodifiableSetFactory {

  //region COLLECTOR
  @SuppressWarnings("unchecked")
  @Override
  public <E> Collector<E, ?, Set<E>> collector() {
    return (Collector<E, ?, Set<E>>) (Collector<E, ?, ?>) ImmutableSet.toImmutableSet();
  }
  //endregion

  //region COPY OF
  @Override
  public <E> Set<E> copyOf(Collection<? extends E> coll) {
    return ImmutableSet.copyOf(coll);
  }
  //endregion

  //region OF
  @Override
  public <E> Set<E> of() {
    return ImmutableSet.of();
  }

  @Override
  public <E> Set<E> of(E e1) {
    return ImmutableSet.of(e1);
  }

  @Override
  public <E> Set<E> of(E e1, E e2) {
    return requireNoDuplicates(2, ImmutableSet.of(e1, e2));
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3) {
    return requireNoDuplicates(3, ImmutableSet.of(e1, e2, e3));
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4) {
    return requireNoDuplicates(4, ImmutableSet.of(e1, e2, e3, e4));
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5) {
    return requireNoDuplicates(5, ImmutableSet.of(e1, e2, e3, e4, e5));
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
    return requireNoDuplicates(6, ImmutableSet.of(e1, e2, e3, e4, e5, e6));
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
    return requireNoDuplicates(7, ImmutableSet.of(e1, e2, e3, e4, e5, e6, e7));
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
    return requireNoDuplicates(8, ImmutableSet.of(e1, e2, e3, e4, e5, e6, e7, e8));
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
    return requireNoDuplicates(9, ImmutableSet.of(e1, e2, e3, e4, e5, e6, e7, e8, e9));
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
    return requireNoDuplicates(10, ImmutableSet.of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10));
  }

  @SafeVarargs
  @Override
  public final <E> Set<E> of(E... elements) {
    return requireNoDuplicates(elements.length, ImmutableSet.copyOf(elements));
  }

  private static <E> Set<E> requireNoDuplicates(int size, ImmutableSet<E> set) {
    if (set.size() < size) {
      throw new IllegalArgumentException("Duplicate element");
    }
    return set;
  }
  //endregion
}
