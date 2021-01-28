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
package pl.tlinkowski.unij.service.collect.jdk10;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.auto.service.AutoService;

import pl.tlinkowski.unij.service.api.UniJService;
import pl.tlinkowski.unij.service.api.collect.UnmodifiableMapFactory;

/**
 * Implementation of {@link UnmodifiableMapFactory} that returns
 * <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Map.html#unmodifiable">unmodifiable
 * maps</a> introduced in JDK 10.
 *
 * @author Tomasz Linkowski
 */
@UniJService(priority = 10)
@AutoService(UnmodifiableMapFactory.class)
public final class Jdk10UnmodifiableMapFactory implements UnmodifiableMapFactory {

  //region COLLECTOR
  @Override
  public <T, K, V> Collector<T, ?, Map<K, V>> collector(Function<? super T, ? extends K> keyMapper,
          Function<? super T, ? extends V> valueMapper) {
    return Collectors.toUnmodifiableMap(keyMapper, valueMapper);
  }

  @Override
  public <T, K, V> Collector<T, ?, Map<K, V>> collector(Function<? super T, ? extends K> keyMapper,
          Function<? super T, ? extends V> valueMapper, BinaryOperator<V> mergeFunction) {
    return Collectors.toUnmodifiableMap(keyMapper, valueMapper, mergeFunction);
  }
  //endregion

  //region COPY OF
  @Override
  public <K, V> Map<K, V> copyOf(Map<? extends K, ? extends V> map) {
    return Map.copyOf(map);
  }
  //endregion

  //region ENTRIES
  @SafeVarargs
  @Override
  public final <K, V> Map<K, V> ofEntries(Map.Entry<? extends K, ? extends V>... entries) {
    return Map.ofEntries(entries);
  }

  @Override
  public <K, V> Map.Entry<K, V> entry(K k, V v) {
    return Map.entry(k, v);
  }
  //endregion

  //region OF
  @Override
  public <K, V> Map<K, V> of() {
    return Map.of();
  }

  @Override
  public <K, V> Map<K, V> of(K k1, V v1) {
    return Map.of(k1, v1);
  }

  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
    return Map.of(k1, v1, k2, v2);
  }

  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
    return Map.of(k1, v1, k2, v2, k3, v3);
  }

  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
    return Map.of(k1, v1, k2, v2, k3, v3, k4, v4);
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
    return Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5);
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
    return Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6);
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) {
    return Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7);
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8,
          V v8) {
    return Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8);
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8,
          V v8, K k9, V v9) {
    return Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8, k9, v9);
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8,
          V v8, K k9, V v9, K k10, V v10) {
    return Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8, k9, v9, k10, v10);
  }
  //endregion
}
