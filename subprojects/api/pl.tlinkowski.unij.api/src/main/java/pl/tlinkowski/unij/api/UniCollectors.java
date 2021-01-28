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
package pl.tlinkowski.unij.api;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import lombok.experimental.UtilityClass;

/**
 * Provides some {@link Collector} factory methods present in {@link java.util.stream.Collectors} class.
 *
 * @author Tomasz Linkowski
 */
@UtilityClass
public final class UniCollectors {

  //region COLLECTIONS

  /**
   * Equivalent of {@link java.util.stream.Collectors#toUnmodifiableList()}.
   */
  public static <T> Collector<T, ?, /*@ReadOnly*/ List<T>> toUnmodifiableList() {
    return UniJ.listFactory().collector();
  }

  /**
   * Equivalent of {@link java.util.stream.Collectors#toUnmodifiableSet()}.
   */
  public static <T> Collector<T, ?, /*@ReadOnly*/ Set<T>> toUnmodifiableSet() {
    return UniJ.setFactory().collector();
  }

  /**
   * Equivalent of {@link java.util.stream.Collectors#toUnmodifiableMap(Function, Function)}.
   */
  public static <T, K, V> Collector<T, ?, /*@ReadOnly*/ Map<K, V>> toUnmodifiableMap(
          Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper) {
    return UniJ.mapFactory().collector(keyMapper, valueMapper);
  }

  /**
   * Equivalent of {@link java.util.stream.Collectors#toUnmodifiableMap(Function, Function, BinaryOperator)}.
   */
  public static <T, K, V> Collector<T, ?, /*@ReadOnly*/ Map<K, V>> toUnmodifiableMap(
          Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper,
          BinaryOperator<V> mergeFunction) {
    return UniJ.mapFactory().collector(keyMapper, valueMapper, mergeFunction);
  }
  //endregion

  //region MISCELLANEOUS

  /**
   * Equivalent of {@link Collectors#flatMapping(Function, Collector)}.
   */
  public static <T, U, A, R> Collector<T, ?, R> flatMapping(
          Function<? super T, ? extends Stream<? extends U>> mapper, Collector<? super U, A, R> downstream) {
    return UniJ.miscProvider().flatMappingCollector(mapper, downstream);
  }

  /**
   * Equivalent of {@link Collectors#filtering(Predicate, Collector)}.
   */
  public static <T, A, R> Collector<T, ?, R> filtering(Predicate<? super T> predicate,
          Collector<? super T, A, R> downstream) {
    return UniJ.miscProvider().filteringCollector(predicate, downstream);
  }
  //endregion
}
