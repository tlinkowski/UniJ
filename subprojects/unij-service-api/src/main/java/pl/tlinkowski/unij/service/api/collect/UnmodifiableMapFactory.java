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
package pl.tlinkowski.unij.service.api.collect;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;

import kotlin.annotations.jvm.ReadOnly;

/**
 * Proxy for
 * <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Map.html#unmodifiable">unmodifiable</a>
 * factory methods available in the {@link Map} interface.
 *
 * @author Tomasz Linkowski
 */
public interface UnmodifiableMapFactory {

  //region COLLECTOR

  /**
   * Equivalent of {@link java.util.stream.Collectors#toUnmodifiableMap(Function, Function)}.
   */
  <T, K, V> Collector<T, ?, /*@ReadOnly*/ Map<K, V>> collector(Function<? super T, ? extends K> keyMapper,
          Function<? super T, ? extends V> valueMapper);

  /**
   * Equivalent of {@link java.util.stream.Collectors#toUnmodifiableMap(Function, Function, BinaryOperator)}.
   */
  <T, K, V> Collector<T, ?, /*@ReadOnly*/ Map<K, V>> collector(Function<? super T, ? extends K> keyMapper,
          Function<? super T, ? extends V> valueMapper, BinaryOperator<V> mergeFunction);
  //endregion

  //region COPY OF

  /**
   * Equivalent of {@link Map#copyOf(Map)}.
   */
  @ReadOnly
  <K, V> Map<K, V> copyOf(@ReadOnly Map<? extends K, ? extends V> map);
  //endregion

  //region ENTRIES

  /**
   * Equivalent of {@link Map#ofEntries(Map.Entry[])}.
   */
  @SuppressWarnings("unchecked")
  @ReadOnly
  <K, V> Map<K, V> ofEntries(@ReadOnly Map.Entry<? extends K, ? extends V>... entries);

  /**
   * Equivalent of {@link Map#entry(Object, Object)}.
   */
  @ReadOnly
  <K, V> Map.Entry<K, V> entry(K k, V v);
  //endregion

  //region OF

  /**
   * Equivalent of {@link Map#of()}.
   */
  @ReadOnly
  <K, V> Map<K, V> of();

  /**
   * Equivalent of {@link Map#of(Object, Object)}.
   */
  @ReadOnly
  <K, V> Map<K, V> of(K k1, V v1);

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object)}.
   */
  @ReadOnly
  <K, V> Map<K, V> of(K k1, V v1, K k2, V v2);

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object)}.
   */
  @ReadOnly
  <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3);

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object, Object, Object)}.
   */
  @ReadOnly
  <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4);

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object)}.
   */
  @SuppressWarnings("PMD.ExcessiveParameterList")
  @ReadOnly
  <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5);

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object,
   * Object)}.
   */
  @SuppressWarnings("PMD.ExcessiveParameterList")
  @ReadOnly
  <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
          K k6, V v6);

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object,
   * Object, Object, Object)}.
   */
  @SuppressWarnings("PMD.ExcessiveParameterList")
  @ReadOnly
  <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
          K k6, V v6, K k7, V v7);

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object,
   * Object, Object, Object, Object, Object)}.
   */
  @SuppressWarnings("PMD.ExcessiveParameterList")
  @ReadOnly
  <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
          K k6, V v6, K k7, V v7, K k8, V v8);

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object,
   * Object, Object, Object, Object, Object, Object, Object)}.
   */
  @SuppressWarnings("PMD.ExcessiveParameterList")
  @ReadOnly
  <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
          K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9);

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object,
   * Object, Object, Object, Object, Object, Object, Object, Object, Object)}.
   */
  @SuppressWarnings("PMD.ExcessiveParameterList")
  @ReadOnly
  <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
          K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10);
  //endregion
}
