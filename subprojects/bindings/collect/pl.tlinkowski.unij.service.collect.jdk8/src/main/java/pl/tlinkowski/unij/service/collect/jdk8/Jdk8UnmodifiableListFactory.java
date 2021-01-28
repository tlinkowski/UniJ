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
import pl.tlinkowski.unij.service.api.collect.UnmodifiableListFactory;

/**
 * Implementation of {@link UnmodifiableListFactory} following the JDK 11
 * <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html#unmodifiable">unmodifiable
 * lists</a> specification (e.g. no {@code null} elements allowed).
 * <p>
 * This implementation returns either an {@link Arrays#asList} wrapped by {@link Collections#unmodifiableList} or a
 * {@link Collections#singletonList}.
 *
 * @author Tomasz Linkowski
 * @implNote To preserve good JVM behavior of this class (no <a href="https://shipilev.net/jvm/anatomy-quarks/16-megamorphic-virtual-calls/">megamorphic
 * calls</a>, only two implementations classes are ever returned from all of the methods. It aligns with JDK 9/10, which
 * returns only {@code ListN} or {@code List12} implementation classes.
 */
@UniJService(priority = 40)
@AutoService(UnmodifiableListFactory.class)
public final class Jdk8UnmodifiableListFactory implements UnmodifiableListFactory {

  private static final List<Object> EMPTY_LIST = unmodArrayAsList();

  //region COLLECTOR

  /**
   * Based on {@link Collectors#toUnmodifiableList()}.
   */
  @Override
  public <E> Collector<E, ?, List<E>> collector() {
    return Collectors.collectingAndThen(Collectors.toList(), this::ofTrustedArrayList);
  }

  /**
   * @implNote We trust {@code list.toArray()} to relinquish the array to us.
   */
  @SuppressWarnings("unchecked")
  private <E> List<E> ofTrustedArrayList(List<E> list) {
    return (List<E>) ofArray(list.toArray(), true);
  }
  //endregion

  //region COPY OF

  /**
   * Based on {@link List#copyOf(Collection)}.
   *
   * @implNote Copies {@param coll} even if it already is unmodifiable (no way to verify this).
   */
  @SuppressWarnings("unchecked")
  @Override
  public <E> List<E> copyOf(Collection<? extends E> coll) {
    return (List<E>) ofArray(coll.toArray(), false);
  }
  //endregion

  //region OF
  @SuppressWarnings("unchecked")
  @Override
  public <E> List<E> of() {
    return (List<E>) EMPTY_LIST;
  }

  @Override
  public <E> List<E> of(@NonNull E e1) {
    return Collections.singletonList(e1);
  }

  @Override
  public <E> List<E> of(@NonNull E e1, @NonNull E e2) {
    return unmodArrayAsList(e1, e2);
  }

  @Override
  public <E> List<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3) {
    return unmodArrayAsList(e1, e2, e3);
  }

  @Override
  public <E> List<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3, @NonNull E e4) {
    return unmodArrayAsList(e1, e2, e3, e4);
  }

  @Override
  public <E> List<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3, @NonNull E e4, @NonNull E e5) {
    return unmodArrayAsList(e1, e2, e3, e4, e5);
  }

  @Override
  public <E> List<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3, @NonNull E e4, @NonNull E e5, @NonNull E e6) {
    return unmodArrayAsList(e1, e2, e3, e4, e5, e6);
  }

  @Override
  public <E> List<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3, @NonNull E e4, @NonNull E e5, @NonNull E e6,
          @NonNull E e7) {
    return unmodArrayAsList(e1, e2, e3, e4, e5, e6, e7);
  }

  @Override
  public <E> List<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3, @NonNull E e4, @NonNull E e5, @NonNull E e6,
          @NonNull E e7, @NonNull E e8) {
    return unmodArrayAsList(e1, e2, e3, e4, e5, e6, e7, e8);
  }

  @Override
  public <E> List<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3, @NonNull E e4, @NonNull E e5, @NonNull E e6,
          @NonNull E e7, @NonNull E e8, @NonNull E e9) {
    return unmodArrayAsList(e1, e2, e3, e4, e5, e6, e7, e8, e9);
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <E> List<E> of(@NonNull E e1, @NonNull E e2, @NonNull E e3, @NonNull E e4, @NonNull E e5, @NonNull E e6,
          @NonNull E e7, @NonNull E e8, @NonNull E e9, @NonNull E e10) {
    return unmodArrayAsList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
  }

  @SafeVarargs
  @Override
  public final <E> List<E> of(E... elements) {
    return ofArray(elements, false);
  }
  //endregion

  /**
   * Based on {@link List#of(Object[])}.
   */
  private <E> List<E> ofArray(E[] elements, boolean trusted) {
    switch (elements.length) {
      case 0:
        return of();
      case 1:
        return of(elements[0]);
      default:
        return ofAtLeastTwoElements(trusted ? elements : elements.clone());
    }
  }

  private <E> List<E> ofAtLeastTwoElements(E[] ownedElements) {
    for (Object element : ownedElements) {
      Objects.requireNonNull(element);
    }
    return unmodArrayAsList(ownedElements);
  }

  // assumes elements are owned and non-null
  @SafeVarargs
  private static <E> List<E> unmodArrayAsList(E... ownedElements) {
    return Collections.unmodifiableList(Arrays.asList(ownedElements));
  }
}
