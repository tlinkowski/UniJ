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
package pl.tlinkowski.unij.service.api.collect;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;

import kotlin.annotations.jvm.ReadOnly;

/**
 * Proxy for
 * <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html#unmodifiable">unmodifiable</a>
 * factory methods available in the {@link List} interface.
 *
 * @author Tomasz Linkowski
 */
public interface UnmodifiableListFactory {

  //region COLLECTOR

  /**
   * Equivalent of {@link java.util.stream.Collectors#toUnmodifiableList()}.
   */
  <E> Collector<E, ?, /*@ReadOnly*/ List<E>> collector();
  //endregion

  //region COPY OF

  /**
   * Equivalent of {@link List#copyOf(Collection)}.
   */
  @ReadOnly
  <E> List<E> copyOf(@ReadOnly Collection<? extends E> coll);
  //endregion

  //region OF

  /**
   * Equivalent of {@link List#of()}.
   */
  @ReadOnly
  <E> List<E> of();

  /**
   * Equivalent of {@link List#of(Object)}.
   */
  @ReadOnly
  <E> List<E> of(E e1);

  /**
   * Equivalent of {@link List#of(Object, Object)}.
   */
  @ReadOnly
  <E> List<E> of(E e1, E e2);

  /**
   * Equivalent of {@link List#of(Object, Object, Object)}.
   */
  @ReadOnly
  <E> List<E> of(E e1, E e2, E e3);

  /**
   * Equivalent of {@link List#of(Object, Object, Object, Object)}.
   */
  @ReadOnly
  <E> List<E> of(E e1, E e2, E e3, E e4);

  /**
   * Equivalent of {@link List#of(Object, Object, Object, Object, Object)}.
   */
  @ReadOnly
  <E> List<E> of(E e1, E e2, E e3, E e4, E e5);

  /**
   * Equivalent of {@link List#of(Object, Object, Object, Object, Object, Object)}.
   */
  @ReadOnly
  <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6);

  /**
   * Equivalent of {@link List#of(Object, Object, Object, Object, Object, Object, Object)}.
   */
  @ReadOnly
  <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7);

  /**
   * Equivalent of {@link List#of(Object, Object, Object, Object, Object, Object, Object, Object)}.
   */
  @ReadOnly
  <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8);

  /**
   * Equivalent of {@link List#of(Object, Object, Object, Object, Object, Object, Object, Object, Object)}.
   */
  @ReadOnly
  <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9);

  /**
   * Equivalent of {@link List#of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object)}.
   */
  @SuppressWarnings("PMD.ExcessiveParameterList")
  @ReadOnly
  <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10);

  /**
   * Equivalent of {@link List#of(Object[])}.
   */
  @SuppressWarnings("unchecked")
  @ReadOnly
  <E> List<E> of(E... elements);
  //endregion
}
