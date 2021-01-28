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
package pl.tlinkowski.unij.service.collect.jdk8;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.auto.service.AutoService;
import lombok.NonNull;

import pl.tlinkowski.unij.service.api.UniJService;
import pl.tlinkowski.unij.service.api.collect.UnmodifiableSetFactory;

/**
 * Implementation of {@link UnmodifiableSetFactory} following the JDK 11
 * <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Set.html#unmodifiable">unmodifiable
 * sets</a> specification (e.g. no {@code null} elements allowed, throws {@link IllegalArgumentException} on duplicate
 * elements provided to {@code of(...)} methods).
 * <p>
 * This implementation returns either a {@link HashSet} wrapped by {@link Collections#unmodifiableSet} or a {@link
 * Collections#singleton}.
 *
 * @author Tomasz Linkowski
 * @implNote To preserve good JVM behavior of this class (no <a href="https://shipilev.net/jvm/anatomy-quarks/16-megamorphic-virtual-calls/">megamorphic
 * calls</a>, only two implementations classes are ever returned from all of the methods. It aligns with JDK 9/10, which
 * returns only {@code SetN} or {@code Set12} implementation classes.
 */
@UniJService(priority = 40)
@AutoService(UnmodifiableSetFactory.class)
public final class Jdk8UnmodifiableSetFactory implements UnmodifiableSetFactory {

  private static final Set<Object> EMPTY_SET = new Builder<>(0).build();

  //region COLLECTOR

  /**
   * Based on {@link Collectors#toUnmodifiableSet()}.
   */
  @Override
  public <E> Collector<E, ?, Set<E>> collector() {
    return Collectors.collectingAndThen(Collectors.toSet(), this::ofTrustedHashSet);
  }
  //endregion

  //region COPY OF

  /**
   * Based on {@link Set#copyOf(Collection)}.
   *
   * @implNote Copies {@param coll} even if it already is unmodifiable (no way to verify this).
   */
  @Override
  public <E> Set<E> copyOf(Collection<? extends E> coll) {
    return ofTrustedHashSet(new HashSet<>(coll));
  }
  //endregion

  //region OF
  @SuppressWarnings("unchecked")
  @Override
  public <E> Set<E> of() {
    return (Set<E>) EMPTY_SET;
  }

  @Override
  public <E> Set<E> of(@NonNull E e1) {
    return Collections.singleton(e1);
  }

  @Override
  public <E> Set<E> of(@NonNull E e1, @NonNull E e2) {
    return new Builder<E>(2)
            .add(e1)
            .add(e2)
            .build();
  }

  @Override
  public <E> Set<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3) {
    return new Builder<E>(3)
            .add(e1)
            .add(e2)
            .add(e3)
            .build();
  }

  @Override
  public <E> Set<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3, @NonNull E e4) {
    return new Builder<E>(4)
            .add(e1)
            .add(e2)
            .add(e3)
            .add(e4)
            .build();
  }

  @Override
  public <E> Set<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3, @NonNull E e4, @NonNull E e5) {
    return new Builder<E>(5)
            .add(e1)
            .add(e2)
            .add(e3)
            .add(e4)
            .add(e5)
            .build();
  }

  @Override
  public <E> Set<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3, @NonNull E e4, @NonNull E e5, @NonNull E e6) {
    return new Builder<E>(6)
            .add(e1)
            .add(e2)
            .add(e3)
            .add(e4)
            .add(e5)
            .add(e6)
            .build();
  }

  @Override
  public <E> Set<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3, @NonNull E e4, @NonNull E e5, @NonNull E e6,
          @NonNull E e7) {
    return new Builder<E>(7)
            .add(e1)
            .add(e2)
            .add(e3)
            .add(e4)
            .add(e5)
            .add(e6)
            .add(e7)
            .build();
  }

  @Override
  public <E> Set<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3, @NonNull E e4, @NonNull E e5, @NonNull E e6,
          @NonNull E e7, @NonNull E e8) {
    return new Builder<E>(8)
            .add(e1)
            .add(e2)
            .add(e3)
            .add(e4)
            .add(e5)
            .add(e6)
            .add(e7)
            .add(e8)
            .build();
  }

  @Override
  public <E> Set<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3, @NonNull E e4, @NonNull E e5, @NonNull E e6,
          @NonNull E e7, @NonNull E e8, @NonNull E e9) {
    return new Builder<E>(9)
            .add(e1)
            .add(e2)
            .add(e3)
            .add(e4)
            .add(e5)
            .add(e6)
            .add(e7)
            .add(e8)
            .add(e9)
            .build();
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <E> Set<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3, @NonNull E e4, @NonNull E e5, @NonNull E e6,
          @NonNull E e7, @NonNull E e8, @NonNull E e9, @NonNull E e10) {
    return new Builder<E>(10)
            .add(e1)
            .add(e2)
            .add(e3)
            .add(e4)
            .add(e5)
            .add(e6)
            .add(e7)
            .add(e8)
            .add(e9)
            .add(e10)
            .build();
  }

  /**
   * Based on {@link Set#of(Object[])}.
   */
  @SafeVarargs
  @Override
  public final <E> Set<E> of(E... elements) {
    switch (elements.length) {
      case 0:
        return of();
      case 1:
        return of(elements[0]);
      default:
        return ofAtLeastTwoElements(elements);
    }
  }
  //endregion

  private <E> Set<E> ofTrustedHashSet(Set<E> set) {
    switch (set.size()) {
      case 0:
        return of();
      case 1:
        return of(set.iterator().next());
      default:
        set.forEach(Objects::requireNonNull);
        return Collections.unmodifiableSet((HashSet<E>) set);
    }
  }

  private <E> Set<E> ofAtLeastTwoElements(E[] elements) {
    Builder<E> builder = new Builder<>(elements.length);
    for (E element : elements) {
      builder.add(Objects.requireNonNull(element));
    }
    return builder.build();
  }

  /**
   * Based on {@code java.util.ImmutableCollections.SetN} constructor.
   * <p>
   * Throws {@link IllegalArgumentException} on duplicate elements.
   */
  private static class Builder<E> {

    private final Set<E> set;

    Builder(int size) {
      this.set = new HashSet<>((int) (size / 0.75)); // based on HashSet(Collection) constructor
    }

    Builder<E> add(E element) {
      if (!set.add(element)) {
        throw new IllegalArgumentException("Duplicate element: " + element);
      }
      return this;
    }

    Set<E> build() {
      return Collections.unmodifiableSet(set);
    }
  }
}
