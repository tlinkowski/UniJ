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
package pl.tlinkowski.unij.api;

import java.util.Collection;
import java.util.List;

import kotlin.annotations.jvm.ReadOnly;
import lombok.experimental.UtilityClass;

/**
 * Provides static factory methods corresponding to those present in {@link List} interface.
 *
 * @author Tomasz Linkowski
 */
@UtilityClass
public final class UniLists {

  //region COPY OF

  /**
   * Equivalent of {@link List#copyOf(Collection)}.
   */
  @ReadOnly
  public static <E> List<E> copyOf(@ReadOnly Collection<? extends E> coll) {
    return UniJ.listFactory().copyOf(coll);
  }
  //endregion

  //region OF

  /**
   * Equivalent of {@link List#of()}.
   */
  @ReadOnly
  public static <E> List<E> of() {
    return UniJ.listFactory().of();
  }

  /**
   * Equivalent of {@link List#of(Object)}.
   */
  @ReadOnly
  public static <E> List<E> of(E e1) {
    return UniJ.listFactory().of(e1);
  }

  /**
   * Equivalent of {@link List#of(Object, Object)}.
   */
  @ReadOnly
  public static <E> List<E> of(E e1, E e2) {
    return UniJ.listFactory().of(e1, e2);
  }

  /**
   * Equivalent of {@link List#of(Object, Object, Object)}.
   */
  @ReadOnly
  public static <E> List<E> of(E e1, E e2, E e3) {
    return UniJ.listFactory().of(e1, e2, e3);
  }

  /**
   * Equivalent of {@link List#of(Object, Object, Object, Object)}.
   */
  @ReadOnly
  public static <E> List<E> of(E e1, E e2, E e3, E e4) {
    return UniJ.listFactory().of(e1, e2, e3, e4);
  }

  /**
   * Equivalent of {@link List#of(Object, Object, Object, Object, Object)}.
   */
  @ReadOnly
  public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5) {
    return UniJ.listFactory().of(e1, e2, e3, e4, e5);
  }

  /**
   * Equivalent of {@link List#of(Object, Object, Object, Object, Object, Object)}.
   */
  @ReadOnly
  public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
    return UniJ.listFactory().of(e1, e2, e3, e4, e5, e6);
  }

  /**
   * Equivalent of {@link List#of(Object, Object, Object, Object, Object, Object, Object)}.
   */
  @ReadOnly
  public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
    return UniJ.listFactory().of(e1, e2, e3, e4, e5, e6, e7);
  }

  /**
   * Equivalent of {@link List#of(Object, Object, Object, Object, Object, Object, Object, Object)}.
   */
  @ReadOnly
  public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
    return UniJ.listFactory().of(e1, e2, e3, e4, e5, e6, e7, e8);
  }

  /**
   * Equivalent of {@link List#of(Object, Object, Object, Object, Object, Object, Object, Object, Object)}.
   */
  @ReadOnly
  public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
    return UniJ.listFactory().of(e1, e2, e3, e4, e5, e6, e7, e8, e9);
  }

  /**
   * Equivalent of {@link List#of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object)}.
   */
  @SuppressWarnings("PMD.ExcessiveParameterList")
  @ReadOnly
  public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
    return UniJ.listFactory().of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
  }

  /**
   * Equivalent of {@link List#of(Object[])}.
   */
  @SuppressWarnings("unchecked")
  @ReadOnly
  public static <E> List<E> of(E... elements) {
    return UniJ.listFactory().of(elements);
  }
  //endregion
}
