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

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.NonNull;

import pl.tlinkowski.unij.service.api.UniJService;
import pl.tlinkowski.unij.service.api.collect.UnmodifiableMapFactory;

/**
 * Implementation of {@link UnmodifiableMapFactory} that returns Guava's {@link ImmutableMap}.
 *
 * @author Tomasz Linkowski
 */
@UniJService(priority = 20)
@AutoService(UnmodifiableMapFactory.class)
public final class GuavaUnmodifiableMapFactory implements UnmodifiableMapFactory {

  //region COLLECTOR
  @Override
  public <T, K, V> Collector<T, ?, Map<K, V>> collector(Function<? super T, ? extends K> keyMapper,
          Function<? super T, ? extends V> valueMapper) {
    return castCollector(ImmutableMap.toImmutableMap(keyMapper, valueMapper));
  }

  @Override
  public <T, K, V> Collector<T, ?, Map<K, V>> collector(Function<? super T, ? extends K> keyMapper,
          Function<? super T, ? extends V> valueMapper, BinaryOperator<V> mergeFunction) {
    return castCollector(ImmutableMap.toImmutableMap(keyMapper, valueMapper, mergeFunction));
  }

  @SuppressWarnings("unchecked")
  private static <T, K, V> Collector<T, ?, Map<K, V>> castCollector(Collector<T, ?, ImmutableMap<K, V>> collector) {
    return (Collector<T, ?, Map<K, V>>) (Collector<T, ?, ?>) collector;
  }
  //endregion

  //region COPY OF
  @Override
  public <K, V> Map<K, V> copyOf(Map<? extends K, ? extends V> map) {
    return ImmutableMap.copyOf(map);
  }
  //endregion

  //region ENTRIES
  @SafeVarargs
  @Override
  public final <K, V> Map<K, V> ofEntries(Map.Entry<? extends K, ? extends V>... entries) {
    return ImmutableMap.copyOf(Arrays.asList(entries));
  }

  @Override
  public <K, V> Map.Entry<K, V> entry(@NonNull K k, @NonNull V v) {
    return Maps.immutableEntry(k, v);
  }
  //endregion

  //region OF
  @Override
  public <K, V> Map<K, V> of() {
    return ImmutableMap.of();
  }

  @Override
  public <K, V> Map<K, V> of(K k1, V v1) {
    return ImmutableMap.of(k1, v1);
  }

  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
    return ImmutableMap.of(k1, v1, k2, v2);
  }

  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
    return ImmutableMap.of(k1, v1, k2, v2, k3, v3);
  }

  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
    return ImmutableMap.of(k1, v1, k2, v2, k3, v3, k4, v4);
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
    return ImmutableMap.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5);
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
    return ImmutableMap.<K, V>builderWithExpectedSize(6)
            .put(k1, v1)
            .put(k2, v2)
            .put(k3, v3)
            .put(k4, v4)
            .put(k5, v5)
            .put(k6, v6)
            .build();
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) {
    return ImmutableMap.<K, V>builderWithExpectedSize(7)
            .put(k1, v1)
            .put(k2, v2)
            .put(k3, v3)
            .put(k4, v4)
            .put(k5, v5)
            .put(k6, v6)
            .put(k7, v7)
            .build();
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8,
          V v8) {
    return ImmutableMap.<K, V>builderWithExpectedSize(8)
            .put(k1, v1)
            .put(k2, v2)
            .put(k3, v3)
            .put(k4, v4)
            .put(k5, v5)
            .put(k6, v6)
            .put(k7, v7)
            .put(k8, v8)
            .build();
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8,
          V v8, K k9, V v9) {
    return ImmutableMap.<K, V>builderWithExpectedSize(9)
            .put(k1, v1)
            .put(k2, v2)
            .put(k3, v3)
            .put(k4, v4)
            .put(k5, v5)
            .put(k6, v6)
            .put(k7, v7)
            .put(k8, v8)
            .put(k9, v9)
            .build();
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8,
          V v8, K k9, V v9, K k10, V v10) {
    return ImmutableMap.<K, V>builderWithExpectedSize(10)
            .put(k1, v1)
            .put(k2, v2)
            .put(k3, v3)
            .put(k4, v4)
            .put(k5, v5)
            .put(k6, v6)
            .put(k7, v7)
            .put(k8, v8)
            .put(k9, v9)
            .put(k10, v10)
            .build();
  }
  //endregion
}
